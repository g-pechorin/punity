package peterlavalle.punity

import peterlavalle.gbt.{Content, TProperTask}
import peterlavalle.{Later, PHile}

class UVulidateTask extends TProperTask.TTaskSingle(
	"verification",
	"checks to be sure that the package can be imported into a fresh project"
) with Later.T with Content with Unity.T {

	// https://docs.unity3d.com/Manual/CommandLineArguments.html

	val expurtTask: Later[List[UExpurtTask]] =
		dependsOn[UExpurtTask]

	dependencyOf("check")

	val unityHandle: Later[Unity] =
		perform {
			new Unity(ext[Config].getUnityHome)(
				(l: String) => System.out.println(l),
				(l: String) => System.err.println(l)
			)
		}

	// create a temporary project
	val tempProject: Later[Unity.Workspace] =
		perform {
			unityHandle createProject (getProject.getBuildDir / getName).FreshFolder
		}

	// copy all content into the temporary project
	contents("punity", "Unity assets that should be resolved and imported") {
		content: PHile =>
			content.toDir(tempProject.root / "Assets")
	}

	// run editor tests as a 'canary' on the temp project
	perform {
		tempProject.runEditorTests(getProject.getBuildDir / s"$getName.log") match {
			case 0 =>
			case r =>
				GradleFail(s"validation returned $r")
		}
	}
}



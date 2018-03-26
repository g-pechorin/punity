package peterlavalle.punity

import peterlavalle.Later
import peterlavalle.gbt.TProperTask

class UEdutorTestsTask extends TProperTask.TTaskSingle(
	"verification",
	"runs any/all editor tests"
) with Unity.T with Later.T {

	dependsOn[UAssumblyEditorTask]
	dependsOn[UAssumblyPluginTask]
	dependsOn[UImpurtTask]

	dependencyOf("test")

	perform {
		workspace.runEditorTests(getProject.getBuildDir / s"$getName.log") match {
			case 0 =>
			case r: Int =>
				GradleFail(
					s"While doing editor tests, I got an unexpected r = $r"
				)
		}
	}
}

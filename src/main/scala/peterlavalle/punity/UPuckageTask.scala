package peterlavalle.punity

import java.io.File

import peterlavalle.Later
import peterlavalle.gbt.TProperTask

class UPuckageTask extends TProperTask.TTaskSingle(
	"publishing",
	"builds a package from assets below our project name"
) with Unity.T with Later.T {

	// https://docs.unity3d.com/Manual/CommandLineArguments.html

	dependsOn[UAssumblyPluginTask]
	dependsOn[UAssumblyEditorTask]
	dependsOn[UExpurtTask]
	dependsOn[UImpurtTask]

	dependencyOf("assemble")

	val puckageFile: Later[File] =
		perform {
			val puckageFile: File =
				getProject.getBuildDir / s"${getProject.getGroup}-${getProject.getName}-${getProject.getVersion.toString}.unitypackage"

			val content: Iterable[String] =
				findSingletonTask[UExpurtTask].assets.list
					.filterNot((_: String) endsWith ".meta")

			GradleFail(
				"""
					|TODO;
					|			System.err.println("TODO; get assets from dependencies")
					|
					|			punityInvoke("-projectPath" :: getProject.getProjectDir :: "-exportPackage" :: content.foldRight(List[AnyRef](puckageFile.EnsureParent, "-quit"))((_: String) :: (_: List[AnyRef]))) {
					|				case 0 =>
					|					puckageFile
					|			}
					|
				""".stripMargin
			)
		}
}

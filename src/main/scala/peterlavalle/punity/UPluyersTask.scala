package peterlavalle.punity

import java.io.File
import java.util

import peterlavalle.Later
import peterlavalle.gbt.TProperTask

import scala.beans.BeanProperty

class UPluyersTask extends TProperTask.TTaskSingle(
	"publishing",
	"builds any/all players configured"
) with Unity.T with Later.T {

	// https://docs.unity3d.com/Manual/CommandLineArguments.html

	dependsOn[UAssumblyPluginTask]
	dependsOn[UImpurtTask]

	val playerBinaries: Later[Array[File]] =
		perform {

			object Binding {

				val taskName: String = getName

				val buildDir: String = getProject.getBuildDir.AbsolutePath

				val projectName: String = getProject.getName

				val scenes: Array[String] =
					if (null == getScenes || getScenes.isEmpty)
						null
					else
						getScenes

				val buildTargets: util.List[BuildTarget] = {
					def selfPlatform: List[BuildTarget] =
						(osName, osArch) match {
							case ("windows", "amd64") =>
								List(BuildTarget.StandaloneWindows, BuildTarget.StandaloneWindows64)
						}

					(if (null == platforms || platforms.isEmpty)
						selfPlatform.toSet
					else
						platforms.toSet.flatMap {
							p: BuildTarget =>
								if (null == p)
									selfPlatform
								else
									List(p)
						}).toList.sortBy((_: BuildTarget).toString)
				}
			}

			sys.error(
				"""
					|
					|			punityEditorScript(Binding) {
					|				case 0 =>
					|					val builds = Binding.buildTargets.map(new File(Binding.buildDir) / _.name())
					|
					|					builds.foreach {
					|						build =>
					|							require(build.isDirectory)
					|							require(2 == build.list().length)
					|					}
					|
					|					builds.toArray
					|			}
					|
	""".stripMargin
			)

		}

	@BeanProperty
	var platforms: Array[BuildTarget] = null

	@BeanProperty
	var scenes: Array[String] = null
}

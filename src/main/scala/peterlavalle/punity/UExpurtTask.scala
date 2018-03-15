package peterlavalle.punity

import java.io.File

import peterlavalle.gbt.{Content, TProperTask}
import peterlavalle.{Later, PHile}

class UExpurtTask extends TProperTask.TTaskSingle(
	"publishing",
	"copies project assets (and .meta) from us into the classpath"
) with Content {

	dependsOn[UAssumblyEditorTask]
	dependsOn[UAssumblyPluginTask]
	dependsOn[UImpurtTask]
	dependsOn[UEdutorTestsTask]

	dependencyOf("classes")

	val assets: Later[PHile] =
		content("punity", "Unity assets that should be resolved and imported") {
			case (_: PHile, out: File) =>

				val assets: PHile =
					PHile.ofFolder(getProject.getProjectDir / ext[Config].assetsRoot)

						// assets that are ours
						.filter((_: String).toLowerCase().startsWith(project.name.toLowerCase() + "/"))

						// assets that aren't in a folder "Test"
						.filter(!(_: String).toLowerCase().contains("/test/"))


				// copy the assets
				assets.toDir(out)

				// we be done!
				assets
		}

}


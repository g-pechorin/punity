package peterlavalle.punity

import javax.inject.Inject

import org.gradle.api.internal.file.SourceDirectorySetFactory
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import peterlavalle.gbt.APlugin

class ThePlugin @Inject()
(
	sourceDirectorySetFactory: SourceDirectorySetFactory
) extends APlugin(sourceDirectorySetFactory) {

	plugin[JavaPlugin]
	plugin[MavenPublishPlugin]

	// configure the sources
	sourceSet("punity")(
		"**/**.fs",
		"**/**.cs"
	)

	// setup our tasks
	install[UAssumblyPluginTask]
	install[UAssumblyEditorTask]
	install[UAssumblyTestingTask]
	install[UExpurtTask]
	install[UImpurtTask]
	install[UPuckageTask]
	install[UPluyersTask]
	install[UEdutorTestsTask]
	install[UVulidateTask]
}

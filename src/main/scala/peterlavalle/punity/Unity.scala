package peterlavalle.punity

import java.io.File

import org.codehaus.plexus.util.cli.{CommandLineUtils, StreamConsumer}
import org.gradle.api
import peterlavalle.gbt.TProperTask

case class Unity(home: File)(val out: StreamConsumer, val err: StreamConsumer) {

	def createProject(root: File): Unity.Workspace = {
		createProject(root, root / "unity-log.txt")
	}

	def createProject(root: File, log: File): Unity.Workspace =
		CommandLineUtils.executeCommandLine(
			root.EnsureExists
				.Shell(UnityIDE)
				.newArgs("-logfile", log)
				.newArgs("-batchmode", "-nographics")
				.newArgs("-createProject", root)
				.newArg("-quit"),
			out, err
		) match {
			case 0 =>
				workspace(root, log)
		}

	def UnityIDE: File = home / Unity.pathIDE

	def workspace(root: File, log: File): Unity.Workspace =
		new Unity.Workspace(this, root)

}

object Unity {

	TODO("simplify usage; we/I have more approaches than I'd like")

	val pathIDE: String =
		osName match {
			case "windows" =>
				"Editor/Unity.exe"
		}

	trait T extends api.Task {

		def workspace: Unity.Workspace =
			this match {
				case task: TProperTask =>
					new Workspace(
						new Unity(task.ext[Config].getUnityHome)(
							(l: String) => System.out.println(l),
							(l: String) => System.err.println(l)
						),
						task.getProject.getProjectDir
					)
			}

	}

	sealed class Workspace
	(
		val unity: Unity,
		val root: File
	) {
		List(
			"ProjectSettings/AudioManager.asset",
			"ProjectSettings/InputManager.asset",
			"ProjectSettings/ProjectVersion.txt"
		).foreach {
			path: String =>
				require((root / path).exists())
		}

		def runEditorTests(log: File): Int =
			CommandLineUtils.executeCommandLine(
				root.EnsureExists
					.Shell(unity.UnityIDE)
					.newArgs(
						"-logfile", log,
						"-batchmode", "-nographics",
						"-projectPath", root,
						"-quit"
					),
				unity.out, unity.err
			)
	}

}

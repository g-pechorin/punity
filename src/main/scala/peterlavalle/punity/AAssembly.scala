package peterlavalle.punity

import java.io.File

import peterlavalle.Later
import peterlavalle.gbt.TProperTask

abstract class AAssembly(description: String) extends TProperTask.TTaskSingle(
	"build",
	description
) {
	val dllOutput: Later[Option[File]] =
		consume(dllPhase, "punity") {
			sourceFiles: Iterable[(File, String)] =>
				sourceFiles.toList.filter { case (_, p) => dllFilter(p) } match {
					case Nil =>
						dllPath.Unlink
						None

					case pluginSource =>
						STUB("compile plugin source(s)")
				}
		}

	def dllPath: File

	def dllPhase: TProperTask.Phase

	def dllFilter: String => Boolean
}


package peterlavalle.punity

import java.io.File

import peterlavalle.gbt.TProperTask
import peterlavalle.gbt.TProperTask.Phase

class UAssumblyTestingTask extends AAssembly(
	"compiles all test/ sources into a DLL (or whatever) then runs it? ... maybe?"
) {
	dependencyOf("test")

	override def dllPath: File = getProject.getBuildDir / s"${getProject.getName}.Testing.dll"

	override def dllPhase: Phase = TProperTask.Phase.Test

	override def dllFilter: String => Boolean = _ => true
}

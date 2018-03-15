
package peterlavalle.punity

import java.io.File

import peterlavalle.gbt.TProperTask
import peterlavalle.gbt.TProperTask.Phase

class UAssumblyPluginTask extends AAssembly(
	"compiled any non-EDITOR main/ sources into a DLL with the UNITY_EDITOR undefined"
) {
	dependencyOf("classes")

	override def dllPath: File = getProject.getProjectDir / s"Assets/${getProject.getName}/Plugin/${getProject.getName}.Plugin.dll"

	override def dllPhase: Phase = TProperTask.Phase.Main

	override def dllFilter: String => Boolean = !(_: String).matches(".*($|\\.|/)Editor(\\.|/).*")
}

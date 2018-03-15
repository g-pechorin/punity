
package peterlavalle.punity

import java.io.File

import peterlavalle.gbt.TProperTask
import peterlavalle.gbt.TProperTask.Phase

class UAssumblyEditorTask extends AAssembly(
	"compiled all main/ sources into a DLL with the UNITY_EDITOR symbol defined"
) {
	dependsOn[UAssumblyPluginTask]

	override def dllPath: File = getProject.getProjectDir / s"Assets/${getProject.getName}/Editor/${getProject.getName}.Editor.dll"

	override def dllPhase: Phase = TProperTask.Phase.Main

	override def dllFilter: String => Boolean = _ => true
}

package peterlavalle.punity

import java.io.File

import scala.beans.BeanProperty

class Config {

	val pathEngine: String =
		osName match {
			case "windows" =>
				"Editor/Data/Managed/UnityEngine.dll"
		}
	val pathEditor: String =
		osName match {
			case "windows" =>
				"Editor/Data/Managed/UnityEditor.dll"
		}
	val pathMcs: String =
		osName match {
			case "windows" =>
				"Editor/Data/MonoBleedingEdge/lib/mono/4.5/mcs.exe"
		}

	val pathIDE: String =
		osName match {
			case "windows" =>
				"Editor/Unity.exe"
		}
	@BeanProperty
	var assetsRoot: String = "Assets/"

	/// used to splitout editor code and plugin code when assembling script binaries
	@BeanProperty
	var editorScript: String = ".*/Editor/.*"

	/// leave null to send the plugins searching
	private var unityHome: File = null

	def UnityEngine: File = getUnityHome / pathEngine

	def UnityEditor: File = getUnityHome / pathEditor

	def UnityMCS: File = getUnityHome / pathMcs

	def UnityIDE: File = getUnityHome / pathIDE

	def getUnityHome: File =
		if (null != unityHome)
			unityHome
		else {
			osName match {
				case "windows" =>
					('C' to 'Z').toStream.flatMap {
						drive: Char =>
							Stream(":/Program Files/Unity/Hub/Editor", ":/Program Files/Unity").flatMap {
								root: String =>
									Stream(
										"/2017.1.3f1",
										"/2017.2.1f1",
										"/2017.3.0f3",
										"/2018.1.0b5",
										""
									).map {
										version: String =>
											new File(drive + root + version).getAbsoluteFile
									}
							}

					}.filter((_: File).exists()) match {
						case found #:: _ =>
							found
					}
			}
		}

	def setUnityHome(file: File): Unit = {
		???
	}
}

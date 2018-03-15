package peterlavalle.punity;

public enum BuildTarget {

	// this is done as .java to make it (more?) accessible to .gradle
	// ... I haven't actually tested that though

	// https://docs.unity3d.com/ScriptReference/BuildTarget.html

	StandaloneOSX(""),
	StandaloneWindows(".exe"),
	iOS(""),
	Android(""),
	StandaloneLinux(""),
	StandaloneWindows64(".exe"),
	WebGL(""),
	WSAPlayer(".exe"),
	StandaloneLinux64(""),
	StandaloneLinuxUniversal(""),
	Tizen(""),
	PSP2(""),
	PS4(""),
	XboxOne(""),
	N3DS(""),
	WiiU(""),
	tvOS(""),
	Switch("");

	public final String extension;

	private BuildTarget(final String extension) {
		this.extension = extension;
	}
}

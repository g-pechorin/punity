using System.Collections;
using System.Collections.Generic;
using UnityEditor;
using UnityEngine;

public static class {{taskName}}
{
	static void BuildPlayer ()
	{
		// list of scenes
		{{#scenes}}
			read seettings
		{{/scenes}}
		{{^scenes}}
			string[] scenes = new string[EditorBuildSettings.scenes.Length];
			for (int i = 0; i < scenes.Length; ++i)
				scenes[i] = EditorBuildSettings.scenes[i].path;
		{{/scenes}}

{{#buildTargets}}
		// {{name}}
			BuildPipeline.BuildPlayer(
					scenes,
					"{{buildDir}}/{{name}}/{{projectName}}{{extension}}",
					BuildTarget.{{name}},
					BuildOptions.None
				);
{{/buildTargets}}
	}
}

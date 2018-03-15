
This is a Gradle plugin to help me automate (some of) the maintenance of my-own Unity3D projects.
It's based on the Gradle Java plugin, and is currently not-very complete.
Maybe [see my blog post on the subject](https://peterlavalle.github.io/post/punity-reworking/)?

## Tasks

### UAssumblyEditorTask (TODO)

compiles all `src/main/punity/*.[cf]s` into an editor dll for Unity

includes:
- `Editor/Data/Managed/UnityEngine.dll`
- `Editor/Data/Managed/UnityEditor.dll`
- `Editor/Data/Managed/UnityEditor.Graphs.dll`

depends:
- `UAssumblyPluginTask`

### UAssumblyPluginTask (TODO)

compiles any non-editor `src/main/punity/*.[cf]s` into a plugin dll for Unity

includes:
- `Editor/Data/Managed/UnityEngine.dll`

### UAssumblyTestingTask (TODO)

compiles all `src/(main|test)/punity/*.[cf]s` into a test assembly

includes:
- `Editor/Data/Managed/UnityEngine.dll`
- `Editor/Data/Managed/UnityEditor.dll`
- `Editor/Data/Managed/UnityEditor.Graphs.dll`

Gradle's `check` depends on this

> ... and so does `testClasses`
> ... i might wrap this in a generated JUnit test at some point

### UPluyersTask (WIP)

depends:
- `UAssumblyPluginTask`
- `UImpurtTask`

### UPuckageTask (WIP)

packs up `Assets/${project.name}/` into `build/${group}-${name}-${version}.unitypackage"`

Gradle's `assemble` depends on this

depends:
- `UAssumblyEditorTask`
- `UAssumblyPluginTask`
- `UImpurtTask`

### UImpurtTask (WIP)

resolves unity packages (thought the content system) and importes them into the project

### UEdutorTestsTask

runs editor tests

depends:
- `UAssumblyEditorTask`
- `UAssumblyPluginTask`
- `UImpurtTask`

### UVulidateTask

checks to be sure that the package can be imported into a fresh project

gradle's `check` depends on this

depends:
- `UPuckageTask`

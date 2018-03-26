package peterlavalle.punity

import java.io.File

import peterlavalle.PHile
import peterlavalle.gbt.TProperTask

class UImpurtTask extends TProperTask.TTaskSingle(
	"build setup",
	"resolves all dependencies and imports them"
) {

	content("punity", "Unity assets that should be resolved and imported") {
		(phile: PHile, _: File) =>
			phile.toDir(getProject.getProjectDir / ext[Config].assetsRoot)
	}
}

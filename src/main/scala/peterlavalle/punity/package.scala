package peterlavalle

import java.io.{InputStream, InputStreamReader, Writer}
import java.net.URL

import com.github.mustachejava.{DefaultMustacheFactory, Mustache, MustacheFactory}

package object punity
	extends peterlavalle.TPackage
		with peterlavalle.TDiagnostics
		with peterlavalle.gbt.TDiagnostics
		with peterlavalle.gbt.TPackage {

	implicit class WrappedMustacheFactory2(self: AnyRef)(implicit mustacheFactory: MustacheFactory = new DefaultMustacheFactory()) {
		def mustache[W <: Writer](name: String): (W => W) = {

			val resource: URL = self.getClass.getResource(
				name
			)

			if (null == resource)
				throw new Exception(
					s"no resource `$name`"
				)


			val inputStream: InputStream =
				resource.openStream()

			if (null == inputStream)
				throw new Exception(
					s"can't open resource ${resource.toString}"
				)

			val mustache: Mustache =
				mustacheFactory.compile(
					new InputStreamReader(
						inputStream
					),
					name
				)

			mustache.execute(_: W, self).asInstanceOf[W]
		}
	}

}

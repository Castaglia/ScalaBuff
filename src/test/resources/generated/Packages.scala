// Generated by ScalaBuff, the Scala protocol buffer compiler. DO NOT EDIT!
// source: packages.proto

package proto

object Packages {
	final case class Packages 
	) extends com.google.protobuf.GeneratedMessageLite
		with hr.sandrogrzicic.scalabuff.runtime.Message[Packages] {


		def writeTo(output: com.google.protobuf.CodedOutputStream) {
		}
		def mergeFrom(m: Packages) = {
			Packages
			)
		}

		def getDefaultInstanceForType = Packages.defaultInstance
		def clear = getDefaultInstanceForType
		def isInitialized = true
		def build = this
		def buildPartial = this
		def newBuilderForType = this
		def toBuilder = this
	}

	object Packages {
		@reflect.BeanProperty val defaultInstance = new Packages()
		def getDefaultInstance = defaultInstance


	}

	def registerAllExtensions(registry: com.google.protobuf.ExtensionRegistryLite) {
	}

}
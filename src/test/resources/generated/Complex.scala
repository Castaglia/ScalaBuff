// Generated by ScalaBuff, the Scala protocol buffer compiler. DO NOT EDIT!
// source: complex.proto

object Complex {
	final case class ComplexMessage (
		firstField: com.google.protobuf.ByteString = com.google.protobuf.ByteString.EMPTY,
		secondField: Option[String] = None,
		nestedOuterField: Option[ComplexMessage.Nested] = None
	) extends com.google.protobuf.GeneratedMessageLite
		with hr.sandrogrzicic.scalabuff.runtime.Message[ComplexMessage] {

		def getSecondField = secondField.getOrElse("")
		def getNestedOuterField = nestedOuterField.getOrElse(null)

		def setSecondField(_f: String) = copy(secondField = _f)
		def setNestedOuterField(_f: ComplexMessage.Nested) = copy(nestedOuterField = _f)

		def clearFirstField = copy(firstField = com.google.protobuf.ByteString.EMPTY)
		def clearSecondField = copy(secondField = None)
		def clearNestedOuterField = copy(nestedOuterField = None)

		def writeTo(output: com.google.protobuf.CodedOutputStream) {
			output.writeBytes(1, firstField)
			if (secondField.isDefined) output.writeString(2, secondField.get)
			if (nestedOuterField.isDefined) output.writeMessage(3, nestedOuterField.get)
		}

		lazy val getSerializedSize = {
			import com.google.protobuf.CodedOutputStream._
			var size = 0
			size += computeBytesSize(1, firstField)
			if (secondField.isDefined) size += computeStringSize(2, secondField.get)
			if (nestedOuterField.isDefined) size += computeMessageSize(3, nestedOuterField.get)

			size
		}

		def mergeFrom(in: com.google.protobuf.CodedInputStream, extensionRegistry: com.google.protobuf.ExtensionRegistryLite): ComplexMessage = {
			import com.google.protobuf.ExtensionRegistryLite.{getEmptyRegistry => _emptyRegistry}
			var _firstField = com.google.protobuf.ByteString.EMPTY
			var _secondField = secondField
			var _nestedOuterField = nestedOuterField

			def _newMerged = ComplexMessage(
				_firstField,
				_secondField,
				_nestedOuterField
			)
			while (true) (in.readTag: @annotation.switch) match {
				case 0 => return _newMerged
				case 10 => _firstField = in.readBytes()
				case 18 => _secondField = in.readString()
				case 26 => in.readMessage(_nestedOuterField.orElse(_nestedOuterField = ComplexTest.ComplexMessage.Nested()).get, _emptyRegistry)
				case default => if (!in.skipField(default)) return _newMerged
			}
			null // compiler needs a return value
		}

		def mergeFrom(m: ComplexMessage) = {
			ComplexMessage(
				m.firstField,
				m.secondField.orElse(secondField),
				m.nestedOuterField.orElse(nestedOuterField)
			)
		}

		def getDefaultInstanceForType = ComplexMessage.defaultInstance
		def clear = getDefaultInstanceForType
		def isInitialized = true
		def build = this
		def buildPartial = this
		def newBuilderForType = this
		def toBuilder = this
	}

	object ComplexMessage {
		@reflect.BeanProperty val defaultInstance = new ComplexMessage()

		val FIRST_FIELD_FIELD_NUMBER = 1
		val SECOND_FIELD_FIELD_NUMBER = 2
		val NESTED_OUTER_FIELD_FIELD_NUMBER = 3

		object SimpleEnum extends hr.sandrogrzicic.scalabuff.runtime.Enum {
			sealed trait EnumVal extends Value
				
			val KEY_NAME = new EnumVal { val name = "KEY_NAME"; val id = 0 }

			val KEY_NAME_VALUE = 0

			def valueOf(id: Int) = (id: @annotation.switch) match {
				case 0 => KEY_NAME
			}
			val internalGetValueMap = new com.google.protobuf.Internal.EnumLiteMap[EnumVal] {
				def findValueByNumber(id: Int): EnumVal = valueOf(id)
			}
		}

		final case class Nested (
			nestedField: String = ""
		) extends com.google.protobuf.GeneratedMessageLite
			with hr.sandrogrzicic.scalabuff.runtime.Message[Nested] {



			def clearNestedField = copy(nestedField = "")

			def writeTo(output: com.google.protobuf.CodedOutputStream) {
				output.writeString(1, nestedField)
			}

			lazy val getSerializedSize = {
				import com.google.protobuf.CodedOutputStream._
				var size = 0
				size += computeStringSize(1, nestedField)

				size
			}

			def mergeFrom(in: com.google.protobuf.CodedInputStream, extensionRegistry: com.google.protobuf.ExtensionRegistryLite): Nested = {
				import com.google.protobuf.ExtensionRegistryLite.{getEmptyRegistry => _emptyRegistry}
				var _nestedField = ""

				def _newMerged = Nested(
					_nestedField
				)
				while (true) (in.readTag: @annotation.switch) match {
					case 0 => return _newMerged
					case 10 => _nestedField = in.readString()
					case default => if (!in.skipField(default)) return _newMerged
				}
				null // compiler needs a return value
			}

			def mergeFrom(m: Nested) = {
				Nested(
					m.nestedField
				)
			}

			def getDefaultInstanceForType = Nested.defaultInstance
			def clear = getDefaultInstanceForType
			def isInitialized = true
			def build = this
			def buildPartial = this
			def newBuilderForType = this
			def toBuilder = this
		}

		object Nested {
			@reflect.BeanProperty val defaultInstance = new Nested()

			val NESTED_FIELD_FIELD_NUMBER = 1

		}
	}

	def registerAllExtensions(registry: com.google.protobuf.ExtensionRegistryLite) {
	}

}
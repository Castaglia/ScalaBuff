// Generated by ScalaBuff, the Scala protocol buffer compiler. DO NOT EDIT!
// source: simpleWithComments.proto

object SimpleWithComments {
	final case class SimpleRequest (
		query: String = "",
		pageNumber: Option[Int] = None,
		resultsPerPage: Option[Int] = None
	) extends com.google.protobuf.GeneratedMessageLite
		with hr.sandrogrzicic.scalabuff.runtime.Message[SimpleRequest] {

		def getPageNumber = pageNumber.getOrElse(0)
		def getResultsPerPage = resultsPerPage.getOrElse(0)

		def writeTo(output: com.google.protobuf.CodedOutputStream) {
			output.writeString(1, query)
			pageNumber.foreach(output.writeInt32(2, _))
			resultsPerPage.foreach(output.writeInt32(3, _))
		}
		def mergeFrom(m: SimpleRequest) = {
			SimpleRequest(
				m.query,
				m.pageNumber.orElse(pageNumber),
				m.resultsPerPage.orElse(resultsPerPage)
			)
		}

		def getDefaultInstanceForType = SimpleRequest.defaultInstance
		def clear = getDefaultInstanceForType
		def isInitialized = true
		def build = this
		def buildPartial = this
		def newBuilderForType = this
		def toBuilder = this
	}

	object SimpleRequest {
		@reflect.BeanProperty val defaultInstance = new SimpleRequest()
		def getDefaultInstance = defaultInstance

		val QUERY_FIELD_NUMBER = 1
		val PAGE_NUMBER_FIELD_NUMBER = 2
		val RESULTS_PER_PAGE_FIELD_NUMBER = 3

	}

	def registerAllExtensions(registry: com.google.protobuf.ExtensionRegistryLite) {
	}

}
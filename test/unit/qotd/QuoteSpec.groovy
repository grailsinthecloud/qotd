package qotd

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Quote)
class QuoteSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "Saving our first quote to the database"() {
	    given: "A brand new quote"
	        def quote = new Quote(author: 'blabla', content: 'blabla')
	    when: "The Quote is saved"
	        quote.save()
	    then: "It saved successfully and can be found in the database"
	        quote.errors.errorCount == 0
	        quote.id != null
	        Quote.get(quote.id).author == quote.author
	}

	def "Trying to save a quote with invalid properties causes an error"() {
	    given: "A new quote without author"
	        def quote = new Quote(content: 'blabla')
	    when: "The Quote is validated"
	        quote.validate()
	    then: "It is not saved and error information can be obtained"
	        quote.hasErrors()
	        quote.errors.errorCount != 0
	        quote.id == null
	        !quote.errors.getFieldError('content')
	        quote.errors.getFieldError('author').code == 'nullable'
	}

}
package qotd

class Quote {

	String content
	String author
	Date created = new Date()

    static constraints = {
	    author nullable: false, blank: false
	    content maxSize: 1000, nullable: false, blank: false
	}

}
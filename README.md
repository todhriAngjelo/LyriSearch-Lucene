# LyriSearch Lucene
![LyriSearch header image](/src/main/resources/LyriSearch header image.PNG)

### ~~~ _A search engine for lyrics, powered by Dionisis & Angjelo_

---

LyriSearch is a powerful Java-based search engine for lyrics, developed specifically for the university course "Information Retrieval". The engine utilizes Lucene Core to provide advanced indexing and search capabilities.

It was developed with the guidance and tutoring of Mrs. [Evaggelia Pitoura](https://www.cs.uoi.gr/~pitoura), Professor at Department of Computer Science and Engineering, University of Ioannina.


With LyriSearch, you can easily search for and discover lyrics from a vast database. Its efficient indexing system ensures that even large collections of lyrics can be searched quickly and accurately. Thanks to the powerful features of Lucene Core, you can also take advantage of advanced search options such as fuzzy matching and phrase searching.

LyriSearch is the perfect tool for anyone who loves music and wants to explore lyrics in new and exciting ways. Try it out today and discover your next favorite song!

- Dionisios Diamantis
- Angjelo Todhri
- ✨ Evaggelia Pitoura ✨

## (Corpus) Details & Credits

- We used data scraping techiniques to collect our data from lyrics.com
- Python script used for the data scraping can be found in the project sources ( .. todo add path )
- Data were extracted in cvs format. We decided to use csv because ... 
  - reason1, 
  - reason2, 
  - reason3
- Each record contains 3 information (attributes). The song title, the singer and the lyrics of he song.
- Python was used to execute the script which will scrap the internet and create a csv file with a sample of 500 song lyrics. The number of the lyrics can be adjusted in the script. 
- todo.... Mention things about scalability

> At this point we have already started using chatGPT to collect information and develop the most optimal way of writting the script.
---- todo 

## Short description of the project.

#### 1. Purpose of the application and the problem it solves

TODO




## Tech

LyriSerach uses a number of open source projects to work properly:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. Java 17 was used because it is the latest version of Java that offers Long Term Support ( LTS ) and we wanted to experience its latest features and have some hands on experience. We could go with Java 8 or Java 11 also. Project can probably run with those versions also.
- [Lucene Core 9.5.0](https://lucene.apache.org/) - Apache Lucene™ is a high-performance, full-featured search engine library written entirely in Java. It is a technology suitable for nearly any application that requires structured search, full-text search, faceting, nearest-neighbor search across high-dimensionality vectors, spell correction or query suggestions.
- [Maven](https://maven.apache.org/) - Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.


And of course LyriSearch Lucene itself is open source with a [soon to be public repository](https://github.com/todhriAngjelo/LyriSearch-Lucene)
on GitHub.

## Installation

LyriSearch requires [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) to run.
Preferably clone the project to your IDE and hit the run button.

```sh
Install InteliJ IDEA
Clone project - File -> New -> Project from Version Control -> Git
Run project - Run -> Run 'Main'
```

## Versions & Updates

LyriSearch is currently on version (todo).
Instructions on how to use them in your own application are linked below.

| Version          | Release Notes                             |
|------------------|-------------------------------------------|
| Version 1        | [plugins/dropbox/README.md][PlDb]         |
| Version 2        | [plugins/github/README.md][PlGh]          |
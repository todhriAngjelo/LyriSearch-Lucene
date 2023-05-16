# LyriSearch Lucene
![LyriSearch header image](\src\main\resources\LyriSearch header image.PNG)

### ~~~ _A search engine for lyrics, powered by Dionisis & Angjelo_

---

LyriSearch is a powerful Java-based search engine for lyrics, developed specifically for the university course "Information Retrieval". The engine utilizes Lucene Core to provide advanced indexing and search capabilities.

It was developed with the guidance and tutoring of Mrs. [Evaggelia Pitoura](https://www.cs.uoi.gr/~pitoura), Professor at Department of Computer Science and Engineering, University of Ioannina.


With LyriSearch, you can easily search for and discover lyrics from a vast database. Its efficient indexing system ensures that even large collections of lyrics can be searched quickly and accurately. Thanks to the powerful features of Lucene Core, you can also take advantage of advanced search options such as fuzzy matching and phrase searching.

LyriSearch is the perfect tool for anyone who loves music and wants to explore lyrics in new and exciting ways. Try it out today and discover your next favorite song!

- Dionisios Diamantis
- Angjelo Todhri
- ✨ Evaggelia Pitoura ✨

The link with the presentation of the project and its basic functionality can be found here:
https://youtu.be/SNSHUosy5qA ( total duration 12:25 minutes )

## (Corpus) Details & Credits

- There are 2 ways, the easy way and the hard way.
- The easy way is to grant and use a dataset that can be found online in the web. There exists websites which provide plenty datasets. One of the most famous is kaggle. For the csv that we will attach in the resources a dataset from Kaggle was used.
  Primary Dataset --> https://www.kaggle.com/datasets/terminate9298/songs-lyrics?select=lyrics.csv
  Second dataset used ( with different MODE you can select which dataset to index) --> https://www.kaggle.com/datasets/deepshah16/song-lyrics-dataset
- The hard way would consist of  data scrapingto collect our data from websites like lyrics.com, azlyrics.com etc
- Python script could be used for the data scraping and data manipulating (sample script can be found at: /src/main/resources )
- Data were extracted in cvs format. We decided to use csv because
  - Compatibility: CSV (Comma-Separated Values) is a widely-used file format that can be easily imported into a wide range of software applications, including spreadsheet software, databases, and programming languages. This makes it an excellent choice for exporting data from your app, as it can be easily accessed and manipulated by users or other software tools
  - Simplicity: CSV is a very simple and lightweight file format, making it easy to understand and work with even for non-technical users. It consists of rows and columns of data, with each row representing a record and each column representing a field. This simplicity also makes it a good choice for storing and transferring large amounts of data
  - Scalability: CSV can be used to store and manage large amounts of data, making it a scalable solution for your app's data management needs. It can handle large datasets without sacrificing performance or requiring specialized hardware or software. This can be particularly important if your app deals with a lot of data or needs to scale up quickly
- Each record contains 3 to 4 information (attributes). The song title, the artist name, the lyrics of the song and the release year.
- Three samples were used for the development and the production of the application.
- One consisting of a few songs ( up to 10) , one consisting of more than 500 song lyrics. One final set of lyrics in different csvs and of different artists was used also.
- Lucene is scalable and it claims to handle big set of data without any issue, with small RAM requirements and modern hardware. Internally we will perform tests in bigger data sets as well. Our main focus is to build a first version of a running application ( prototype )

## Basic Functionalities offered to the user ( Use cases )

- Lyrics search. Search lyrics from a wide range of lyrics dataset consisting of many artists. For each new csv data structures a new mapping has to be implemented inside the application. There are currently 2 mappings for 2 different csv structures implemented.
- Lyrics search based on search attribute. Search based on Song title, Song Artist or a keyword from the songs lyrics.
- Show of results. Results are displayed and the search term is highlighted at the results. Pagination is supported and grouping based on the release year of the song.
- Results pages is opened in a new tab. The user can open the results in a new tab and navigate through the results. Each result has different color for better user experience.
- Recommendation of songs. The user can get a recommendation of songs based on his search term history. His most recent search terms are combined to create a new query to Lucene and the results are displayed to the user.
- Indexing functionality is offered through a button. User has to click on the index button only in the first time he uses the application. After that the index is created and the user can search for lyrics.

## Main views of the application

![LyriSearch header image](\src\main\resources\Main screen.png)

![LyriSearch header image](\src\main\resources\Show results screen.png)

![LyriSearch header image](\src\main\resources\Recommended song screen.png)



## Short description of the project.

#### Introduction:
The goal of the system is to allow users to search for song lyrics using various search criteria such as song title, artist name, and lyrics keywords. The app will leverage Lucene's text analysis and indexing capabilities to provide efficient and accurate search results.

#### Text analysis and index construction:
The app will preprocess the lyrics data by applying various text analysis techniques such as tokenization, stemming, and stop word removal using Lucene's Analyzer API. The resulting documents will be indexed using Lucene's IndexWriter API, which will create an inverted index that maps terms to the documents in which they appear. The documents will have fields such as song title, artist name, lyrics, and release year to support different search modes. The app will also use Lucene's QueryParser API to parse user input and create corresponding query objects to search the index.


#### Search:
The app will support various types of queries such as term queries, phrase queries, fuzzy queries, and wildcard queries using Lucene's Query API. The app will use Lucene's IndexSearcher API to execute the queries and retrieve matching documents from the index.

#### Presentation of results:
The app will present the search results in a user-friendly format such as a list or table. The app will also provide snippets of the matching lyrics to highlight the relevant parts of the song. Pagination will be supported and groupping based on releae year of the song.


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
Run gui.CentralWindow class with Java 17++
```

## Versions & Updates

LyriSearch is currently on version (todo).
Instructions on how to use them in your own application are linked below.

| Version   | Release Notes                                                              |
|-----------|----------------------------------------------------------------------------|
| Version 1.0 | initial commit , commit readme and app logo                              |
| Version 2.0 | commit readme update, lyrisearch headermage and pom.xml ( maven support )|
| Version 2.1 | redme support, some screenshots commit source code for the swing main page|
| Version 2.2 | readme finalization with small intro of the program and csv file         |
| Version 3 | final application files, java impl files, final csv files, new and finalized guis   |

### Sample python script that could be used to scrape lyrics from a website(not used):

    import requests
    from bs4 import BeautifulSoup
    import csv
    import time

    # List of URLs to scrape
    urls = ['https://www.azlyrics.com/lyrics/michaeljackson/takemeback.html',
    'https://www.azlyrics.com/lyrics/madonna/likeaprayer.html',
    'https://www.azlyrics.com/lyrics/queen/bohemianrhapsody.html']

    # Open a CSV file for writing
    with open('song_lyrics.csv', 'w', newline='') as csvfile:
    writer = csv.writer(csvfile, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)

    # Write the header row
    writer.writerow(['artist', 'song', 'lyrics'])

    # Loop through the list of URLs
    for url in urls:
    # Send a GET request to the URL
    response = requests.get(url)

    # Parse the HTML content using BeautifulSoup
    soup = BeautifulSoup(response.content, 'html.parser')

    # Extract the song title and artist name from the page title
    title = soup.title.string
    artist, song = title.split(' - ')

    # Extract the lyrics text from the page
    lyrics = soup.find('div', class_='ringtone').find_next_sibling('div').text.strip()

    # Write the data row for this URL
    writer.writerow([artist, song, lyrics])

    # Print a success message
    print(f"Song '{song}' successfully scraped.")

    # Pause for 10 seconds before scraping the next URL
    time.sleep(10)
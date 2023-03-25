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
## Tech

LyriSerach uses a number of open source projects to work properly:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. Java 17 was used because it is the latest version of Java that offers Long Term Support ( LTS ) and we wanted to experience its latest features and have some hands on experience. We could go with Java 8 or Java 11 also. Project can probably run with those versions also.
- [Lucene Core 9.5.0](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - Apache Lucene™ is a high-performance, full-featured search engine library written entirely in Java. It is a technology suitable for nearly any application that requires structured search, full-text search, faceting, nearest-neighbor search across high-dimensionality vectors, spell correction or query suggestions.
- [Maven]() - Markdown parser done right. Fast and easy to extend.


And of course Dillinger itself is open source with a [public repository][dill]
on GitHub.

## Installation

Dillinger requires [Node.js](https://nodejs.org/) v10+ to run.

Install the dependencies and devDependencies and start the server.

```sh
cd dillinger
npm i
node app
```

For production environments...

```sh
npm install --production
NODE_ENV=production node app
```

## Plugins

Dillinger is currently extended with the following plugins.
Instructions on how to use them in your own application are linked below.

| Plugin | README |
| ------ | ------ |
| Dropbox | [plugins/dropbox/README.md][PlDb] |
| GitHub | [plugins/github/README.md][PlGh] |
| Google Drive | [plugins/googledrive/README.md][PlGd] |
| OneDrive | [plugins/onedrive/README.md][PlOd] |
| Medium | [plugins/medium/README.md][PlMe] |
| Google Analytics | [plugins/googleanalytics/README.md][PlGa] |

## Development

Want to contribute? Great!

Dillinger uses Gulp + Webpack for fast developing.
Make a change in your file and instantaneously see your updates!

Open your favorite Terminal and run these commands.

First Tab:

```sh
node app
```

Second Tab:

```sh
gulp watch
```

(optional) Third:

```sh
karma test
```

#### Building for source

For production release:

```sh
gulp build --prod
```

Generating pre-built zip archives for distribution:

```sh
gulp build dist --prod
```

## Docker

Dillinger is very easy to install and deploy in a Docker container.

By default, the Docker will expose port 8080, so change this within the
Dockerfile if necessary. When ready, simply use the Dockerfile to
build the image.

```sh
cd dillinger
docker build -t <youruser>/dillinger:${package.json.version} .
```

This will create the dillinger image and pull in the necessary dependencies.
Be sure to swap out `${package.json.version}` with the actual
version of Dillinger.

Once done, run the Docker image and map the port to whatever you wish on
your host. In this example, we simply map port 8000 of the host to
port 8080 of the Docker (or whatever port was exposed in the Dockerfile):

```sh
docker run -d -p 8000:8080 --restart=always --cap-add=SYS_ADMIN --name=dillinger <youruser>/dillinger:${package.json.version}
```

> Note: `--capt-add=SYS-ADMIN` is required for PDF rendering.

Verify the deployment by navigating to your server address in
your preferred browser.

```sh
127.0.0.1:8000
```

## License

MIT

**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[dill]: <https://github.com/joemccann/dillinger>
[git-repo-url]: <https://github.com/joemccann/dillinger.git>
[john gruber]: <http://daringfireball.net>
[df1]: <http://daringfireball.net/projects/markdown/>
[markdown-it]: <https://github.com/markdown-it/markdown-it>
[Ace Editor]: <http://ace.ajax.org>
[node.js]: <http://nodejs.org>
[Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
[jQuery]: <http://jquery.com>
[@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
[express]: <http://expressjs.com>
[AngularJS]: <http://angularjs.org>
[Gulp]: <http://gulpjs.com>

[PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
[PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
[PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
[PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
[PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
[PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>

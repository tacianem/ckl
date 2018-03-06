Android Project for CheeseCake Labs :)

This project displays a list of articles (downloaded from the Internet) to the user.
***Features: Mark as read, and sort. 

- The app shows loading, empy list, and 'no Internet' screens as a feedback to the user.
- This version does not have any particular dependencies or database configuration.
- No documentation is given directly in the classes, as the variables' names specify their purpose already.

Documentation, thus, is given through the following summary:

* Article.java - An article object contains all provided/needed information for an article, such as: title, author, date, website, contents, image URL, tags, and a flag to denote whether it has been already read.

* ArticleAdapter.java - Inflates layout article_item, and is responsible for the display of the list fo articles. It also calls the image loader in order to download the images for each article, saving them in the cache.

* ArticleDetails.java - Activity which shows the articles in more details, having its contents displayed for instance. It inflates the article_details layout, and reads info about the current article through the serializable interface.

* ArticleImageLoader.java - Asynchronous loader responsible for the downloading of the articles' images. It keeps track of the URL and the image view of the article in question.

* ArticlesActivity.java - Parent of ArticleDetails and ArticleSorting, it is the main activity. It controls all the flow of the app. Inflates article_list, and responds to sort selections via three dots menu, and marks as read as well (basically removes the bold from the current article's title and author in the list). Note: although a cache is used, no database is not (so far). So, when closing and re-opening the app, it'll consider all articles as not read. 

* ArticleSorting.java - Activity responsible for storing the sorting option selected by the user and passingit back to ArticlesActivity via intents. Inflates article_sorting, displaying the sort options for the user to choose (author, title, date, website, tag or contents).

* Cache.java - Singleton which allocates an amount of memory for the storing of articles' images.

* JsonLoader.java - Asynchronous loader in charge of fetching the json file from the given JSON_URL and sending its String back to ArticlesActivity.

* JsonQueryUtils.java - Parses the json String, creating each Article and adding it to the list of articles.

* Tag.java - Simple class standing for the Tag array in the Json file. It has ID, label and color info of its corresponding article.


In case of doubts contact Taciane Martimiano.
E-mail: tacianeemartimiano@gmail.com
Github: tacianem

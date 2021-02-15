from urllib.request import Request, urlopen

# Create request to allow for scraping
req = Request('https://theyoungauthors.com/random-submission/', headers={'User-Agent': 'Mozilla/5.0'})
webpage = urlopen(req).read()

# Decode for string manipulation
mystr = webpage.decode("utf8")
post = ""
post_title = ""
post_author = ""

# Make sure there is an entry content div to retrieve content from
if("<div class=\"entry-content\" itemprop=\"articleBody\">" in mystr):

    # Retrieve first the post title
    x = mystr.find("<meta property=\"og:title\" content=")
    y = mystr[x:].find(">")
    post_title = mystr[x+len("<meta property=\"og:title\" content="):x+y].replace("/", "").replace("\"", "")

    # Then post author
    x = mystr.find("<i class=\"icon-user\"></i> by  <span itemprop=\"author\">")
    y = mystr[x:].find("rel=\"author\">")
    z = mystr[x+y:].find("</a>")
    post_author = mystr[x + y: z+x+y].replace("rel=\"author\">", "")

    # Then the main content
    for i in range(len(mystr)):
        if(mystr[i:i+50] == "<div class=\"entry-content\" itemprop=\"articleBody\">"):
            j = i + 50

            # Retrieve only the relevant content from that div
            while mystr[j:j+6] != "</div>":
                post += mystr[j]
                j+=1
            break

# Replace any extra HTML tagging
if(len(post) > 0):
    scraped_post = post.replace("<p class=\"font_9\">", "")
    post = scraped_post.replace("<span style=\"text-decoration: underline;\"><strong>", "")
    scraped_post = post.replace("</strong></span>", "")
    post = scraped_post.replace("</p>", "")
    scraped_post = post.replace("&#8217;", "'")
    post = scraped_post.replace("<p class=\"font_8\">", "")
    scraped_post = post.replace("<br />", "\n").replace("<br/>", "\n")
    post = scraped_post.replace("<p>", "")
    scraped_post = post.replace("<div id='jp-relatedposts' class='jp-relatedposts' >", "")
    post = scraped_post.replace("<h3 class=\"jp-relatedposts-headline\"><em>Related</em></h3>", "")
    scraped_post = post.lstrip()
    post = scraped_post.replace("<p style=\"text-align: justify;\">", "").replace("<figure class=\"wp-block-image\">", "")
    post = scraped_post

author_string = ""

# Get the author string if present
if "<em>" in post:
    x = post.find("<em>")
    y = post.find("</em>")
    author_string = post[x+4:y]
    scraped_post = post.replace(author_string, "")
    post = scraped_post
    scraped_post = post.replace("<em>", "")
    post = scraped_post.replace("</em>", "")

image_url = ""

# Last but not least, get the image link if present
if "<a href=" in post:
    x = post.find("srcset=\"")
    y = post[x:].find(" ")
    image_url = post[x+len("srcset=\""): x + y]

elif "src=\"" in post:
    x = post.find("src=\"")
    y = post[x:].find("\" ")
    image_url = post[x+len("src=\""): x + y]

# A little more stripping
x = post.find("<a href=")
if(x != -1):
    post = post[:x]
x = post.find("<img loading=")
if(x != -1):
    post = post[:x]

print("Title: " + post_title)
print("Author: " + post_author)
print("Post: \n" + post)
print("Author string: " + author_string)
print("Image URL: " + image_url)
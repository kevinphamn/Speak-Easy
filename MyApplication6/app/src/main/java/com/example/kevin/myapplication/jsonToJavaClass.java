package com.example.kevin.myapplication;

/**
 * Created by Kevin on 9/3/2015.
 */
public class jsonToJavaClass {
    private String version;
    private List list;

    public jsonToJavaClass() {
    }

    public jsonToJavaClass(String version, List list) {
        this.version = version;
        this.list = list;
    }

    public String getVersion() {
        return version;
    }

    public List getList() {
        return list;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setList(List list) {
        this.list = list;
    }

    public class List {
        private Object title;
        private Object teaser;
        private Object miniTeaser;
        private Object[] link;
        private story[] story;

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public Object getTeaser() {
            return teaser;
        }

        public void setTeaser(Object teaser) {
            this.teaser = teaser;
        }

        public Object getMiniTeaser() {
            return miniTeaser;
        }

        public void setMiniTeaser(Object miniTeaser) {
            this.miniTeaser = miniTeaser;
        }

        public Object[] getLink() {
            return link;
        }

        public void setLink(Object[] link) {
            this.link = link;
        }

        public story[] getStory() {
            return story;
        }

        public void setStory(story[] story) {
            this.story = story;
        }


        public class story{

            private String id;
            private Title title;
            private Text text;
            private Link[] link;
            /**private Object title;
            private Object subtitle;
            private Object shortTitle;
            private Object teaser;
            private Object miniTeaser;
            private Object slug;
            private Object thumbnail;
            private Object storyDate;
            private Object pubDate;
            private Object lastModifiedDate;
            private Object audioRunByDate;
            private Object[] show;
            private Object keywords;
            private Object priorityKeywords;
            private Object[] organization;
            private Object[] parent;
            private Object[] audio;
            private Object[] byline;
            private Object[] container;
            private Object[] image;
            private Object[] relatedLink;
            private Object textWithHtml;
            private Object fullText;**/

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Link[] getLink() {
                return link;
            }

            public void setLink(Link[] link) {
                this.link = link;
            }

            public Title getTitle() {
                return title;
            }

            public void setTitle(Title title) {
                this.title = title;
            }
            /**
            public Object getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(Object subtitle) {
                this.subtitle = subtitle;
            }

            public Object getShortTitle() {
                return shortTitle;
            }

            public void setShortTitle(Object shortTitle) {
                this.shortTitle = shortTitle;
            }

            public Object getTeaser() {
                return teaser;
            }

            public void setTeaser(Object teaser) {
                this.teaser = teaser;
            }

            public Object getMiniTeaser() {
                return miniTeaser;
            }

            public void setMiniTeaser(Object miniTeaser) {
                this.miniTeaser = miniTeaser;
            }

            public Object getSlug() {
                return slug;
            }

            public void setSlug(Object slug) {
                this.slug = slug;
            }

            public Object getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(Object thumbnail) {
                this.thumbnail = thumbnail;
            }

            public Object getStoryDate() {
                return storyDate;
            }

            public void setStoryDate(Object storyDate) {
                this.storyDate = storyDate;
            }

            public Object getPubDate() {
                return pubDate;
            }

            public void setPubDate(Object pubDate) {
                this.pubDate = pubDate;
            }

            public Object getLastModifiedDate() {
                return lastModifiedDate;
            }

            public void setLastModifiedDate(Object lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
            }

            public Object getAudioRunByDate() {
                return audioRunByDate;
            }

            public void setAudioRunByDate(Object audioRunByDate) {
                this.audioRunByDate = audioRunByDate;
            }

            public Object[] getShow() {
                return show;
            }

            public void setShow(Object[] show) {
                this.show = show;
            }

            public Object getKeywords() {
                return keywords;
            }

            public void setKeywords(Object keywords) {
                this.keywords = keywords;
            }

            public Object getPriorityKeywords() {
                return priorityKeywords;
            }

            public void setPriorityKeywords(Object priorityKeywords) {
                this.priorityKeywords = priorityKeywords;
            }

            public Object[] getOrganization() {
                return organization;
            }

            public void setOrganization(Object[] organization) {
                this.organization = organization;
            }

            public Object[] getParent() {
                return parent;
            }

            public void setParent(Object[] parent) {
                this.parent = parent;
            }

            public Object[] getAudio() {
                return audio;
            }

            public void setAudio(Object[] audio) {
                this.audio = audio;
            }

            public Object[] getByline() {
                return byline;
            }

            public void setByline(Object[] byline) {
                this.byline = byline;
            }

            public Object[] getContainer() {
                return container;
            }

            public void setContainer(Object[] container) {
                this.container = container;
            }

            public Object[] getImage() {
                return image;
            }

            public void setImage(Object[] image) {
                this.image = image;
            }

            public Object[] getRelatedLink() {
                return relatedLink;
            }

            public void setRelatedLink(Object[] relatedLink) {
                this.relatedLink = relatedLink;
            }**/

            public Text getText() {
                return text;
            }

            public void setText(Text text) {
                this.text = text;
            }

            /**
            public Object getTextWithHtml() {
                return textWithHtml;
            }

            public void setTextWithHtml(Object textWithHtml) {
                this.textWithHtml = textWithHtml;
            }

            public Object getFullText() {
                return fullText;
            }

            public void setFullText(Object fullText) {
                this.fullText = fullText;
            }**/

            public class Title {

                private String $text;

                public String get$text() {
                    return $text;
                }

                public void set$text(String $text) {
                    this.$text = $text;
                }
            }

            public class Text {
                private paragraph[] paragraph;

                public paragraph[] getParagraph() {
                    return paragraph;
                }

                public void setParagraph(paragraph[] paragraph) {
                    this.paragraph = paragraph;
                }

                public class paragraph{
                    private String num;
                    private String $text;

                    public String getNum() {
                        return num;
                    }

                    public void setNum(String num) {
                        this.num = num;
                    }

                    public String get$text() {
                        return $text;
                    }

                    public void set$text(String $text) {
                        this.$text = $text;
                    }
                }
            }

            public class Link {
                private String type;
                private String $text;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String get$text() {
                    return $text;
                }

                public void set$text(String $text) {
                    this.$text = $text;
                }
            }
        }
    }
}


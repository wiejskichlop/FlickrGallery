package com.example.wiejskichlop.flickrgallery;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss", strict = false)
public class ArticleResponse {

    @Element(name = "channel")
    public Channel channel;

    public class Channel {

        @ElementList
        public List<Article> articles;
    }
}
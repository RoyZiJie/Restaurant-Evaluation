

package io.renren.common.xss;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

/**
 * XSS filter tool class
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public class XssUtils extends Safelist {

    /**
     * XSS filtering
     */
    public static String filter(String html){
        return Jsoup.clean(html, xssWhitelist());
    }

    /**
     * XSS filter whitelist
     */
    private static Safelist xssWhitelist(){
        return new Safelist()
            //Supported tags
            .addTags("a", "b", "blockquote", "br", "caption", "cite", "code", "col", "colgroup", "dd", "div", "dl",
                    "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6", "i", "img", "li", "ol", "p", "pre", "q", "small",
                    "strike", "strong","sub", "sup", "table", "tbody", "td","tfoot", "th", "thead", "tr", "u","ul",
                    "embed","object","param","span")

            //Supported tags属性
            .addAttributes("a", "href", "class", "style", "target", "rel", "nofollow")
            .addAttributes("blockquote", "cite")
            .addAttributes("code", "class", "style")
            .addAttributes("col", "span", "width")
            .addAttributes("colgroup", "span", "width")
            .addAttributes("img", "align", "alt", "height", "src", "title", "width", "class", "style")
            .addAttributes("ol", "start", "type")
            .addAttributes("q", "cite")
            .addAttributes("table", "summary", "width", "class", "style")
            .addAttributes("tr", "abbr", "axis", "colspan", "rowspan", "width", "style")
            .addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width", "style")
            .addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope","width", "style")
            .addAttributes("ul", "type", "style")
            .addAttributes("pre", "class", "style")
            .addAttributes("div", "class", "id", "style")
            .addAttributes("embed", "src", "wmode", "flashvars", "pluginspage", "allowFullScreen", "allowfullscreen",
                "quality", "width", "height", "align", "allowScriptAccess", "allowscriptaccess", "allownetworking", "type")
            .addAttributes("object", "type", "id", "name", "data", "width", "height", "style", "classid", "codebase")
            .addAttributes("param", "name", "value")
            .addAttributes("span", "class", "style")

            //Protocol corresponding to tag attributes
            .addProtocols("a", "href", "ftp", "http", "https", "mailto")
            .addProtocols("img", "src", "http", "https")
            .addProtocols("blockquote", "cite", "http", "https")
            .addProtocols("cite", "cite", "http", "https")
            .addProtocols("q", "cite", "http", "https")
            .addProtocols("embed", "src", "http", "https");
    }

    public static void main(String[] args) {
        StringBuilder html = new StringBuilder();
        System.out.println(filter(html.toString()));
    }

}
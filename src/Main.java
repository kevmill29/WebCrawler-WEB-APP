import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;



public class Main {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated Method stub
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a valid url :");
        String url= input.nextLine();
        crawl(1, url, new ArrayList<String>());
    }

    private static void crawl(int level, String url, ArrayList<String>visited) throws IOException {
        if(level <= 5){
    Document doc = request(url, visited);

    if(doc!= null){
        for(Element link: doc.select("a[href]")){
            String next_link = link.absUrl("href");
            if(!visited.contains(next_link)){
                crawl(level++, next_link, visited);
            }
        }
    }
        }
    }

    private static Document request(String url, ArrayList<String>v) throws IOException {
        Connection con = Jsoup.connect(url);
        Document doc = con.get();
        if(con.response().statusCode()== 200){
            System.out.println("Link: "+ url);
            System.out.println(doc.title());
            v.add(url);
            return doc;
        }
        return null;
    }
}
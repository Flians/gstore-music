package com.flian.gstoremusic.web;

import com.flian.gstoremusic.model.DBInfo;
import com.flian.gstoremusic.model.Entry;
import com.flian.gstoremusic.model.Music;
import com.flian.gstoremusic.service.MusicService;
import com.flian.gstoremusic.service.impl.MusicServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    MusicService musicService = new MusicServiceImpl();

    @RequestMapping("/")
    public String index() {
        return "redirect:/bySparql";
    }

    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(required = false,defaultValue="1",value="pageNum") Integer pageNum,
                       @RequestParam(defaultValue="10",value="pageSize") Integer pageSize) throws Exception {
        if(pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if(pageSize == null) {
            pageSize = 10;
        }
        List<Music> musics = musicService.getAllMusic(pageNum, pageSize);
        model.addAttribute("musics", musics);

        DBInfo db = musicService.getDBInfo("music");
        model.addAttribute("dbInfo", db);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        int[] navigatePageNum = new int[5];
        int base = (pageNum-1)/5;
        int totalPages = (int) ((db.getTriple_num()+pageSize-1)/pageSize);
        model.addAttribute("totalPages", totalPages);
        for(int i=0; i <5; i++) {
            int temp = base*5 + i + 1;
            if(temp <= totalPages)
                navigatePageNum[i] = temp;
            else
                break;
        }
        model.addAttribute("navigatePageNum", navigatePageNum);
        return "music/list";
    }

    @RequestMapping("/bySparql")
    public String bySparql(Model model,
                           String sparql,
                           @RequestParam(required = false,defaultValue="1",value="pageNum") Integer pageNum,
                           @RequestParam(defaultValue="10",value="pageSize") Integer pageSize) throws Exception {
        if(pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if(pageSize == null) {
            pageSize = 10;
        }
        model.addAttribute("sparql", sparql);

        if(sparql == null|| sparql.isEmpty())
            sparql= String .format("SELECT * WHERE {?s ?p ?o} LIMIT %s OFFSET %s", pageSize, (pageNum-1)*pageSize);
        else if (sparql.toUpperCase().indexOf("LIMIT")==-1)
            sparql = sparql + " LIMIT " + pageSize + " OFFSET " + (pageNum-1)*pageSize;
        Map<String, List<Map<String,Entry>>> musics = musicService.findMusicBySparql(sparql, pageNum, pageSize);
        model.addAttribute("result", musics);
        DBInfo db = musicService.getDBInfo("music");
        model.addAttribute("dbInfo", db);

        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        int[] navigatePageNum = new int[5];
        int base = (pageNum-1)/5;
        int totalPages = (int) ((db.getTriple_num()+pageSize-1)/pageSize);
        model.addAttribute("totalPages", totalPages);
        for(int i=0; i <5; i++) {
            int temp = base*5 + i + 1;
            if(temp <= totalPages)
                navigatePageNum[i] = temp;
            else
                break;
        }
        model.addAttribute("navigatePageNum", navigatePageNum);
        return "music/query";
    }
}

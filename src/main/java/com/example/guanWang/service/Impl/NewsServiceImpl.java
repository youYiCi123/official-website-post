package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.dto.NewsAddParam;
import com.example.guanWang.dto.NewsParam;
import com.example.guanWang.mapper.NewsContentMapper;
import com.example.guanWang.mapper.NewsMapper;
import com.example.guanWang.model.*;
import com.example.guanWang.service.NewsService;
import com.example.guanWang.service.UmsAdminCacheService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsContentMapper newsContentMapper;

    @Autowired
    private UmsAdminCacheService umsAdminCacheService;
    @Override
    public List<NewsBrieflyInitial> getNewsByDateAndKeyword(String startDate, String endDate, String keyword,Long newsType) {

        return newsMapper.getTrainByDateAndKeyword(startDate,  endDate,  keyword,newsType);
    }

    @Override
    public List<NewsShow> getNewsByType(Long newsType) {
        return newsMapper.getNewsByType(newsType);
    }

    @Override
    public int delete(Long trainId) {
        int count = newsMapper.delete(trainId);
        if (count > 0) {
            return newsContentMapper.delete(trainId);
        }
        return -1;
    }

    @Override
    public int deleteBatchNews(List<Long> idList) {
        int count = newsMapper.deleteBatchTrain(idList);
        if (count > 0) {
            return newsContentMapper.deleteBatchNews(idList);
        }
        return -1;
    }

    @Override
    public NewsParam queryNewsContent(Long id) {
        return newsContentMapper.queryNewsContent(id);
    }

    @Override
    public NewsPreDetail queryNewsPreContent(Long id) {
        NewsPreDetail newsPreDetail = newsContentMapper.queryNewsPreContent(id);
        if(newsPreDetail!=null){
            newsMapper.setViewCount(id);
        }
        return newsPreDetail;
    }

    @Override
    public NewsLinkModel getNewsSort(Long id) {

        List<NewsLink> newsSortList = newsMapper.getNewsSort(id);
        NewsLink[] newsSort = newsSortList.stream().toArray(NewsLink[]::new);
        NewsLinkModel newsLinkModel = new NewsLinkModel();
        if(newsSort.length==2){
            newsLinkModel.setPreLink("/news/NewsDetail?newsId="+newsSort[0].getId());
            newsLinkModel.setPreTheme(newsSort[0].getTheme());
            newsLinkModel.setPostLink("/news/NewsDetail?newsId="+newsSort[1].getId());
            newsLinkModel.setPostTheme(newsSort[1].getTheme());
        }else if(id<newsSort[0].getId()){
            newsLinkModel.setPreLink("javascript:void(0);");
            newsLinkModel.setPreTheme("无");
            newsLinkModel.setPostLink("/news/NewsDetail?newsId="+newsSort[0].getId());
            newsLinkModel.setPostTheme(newsSort[0].getTheme());
        }else{
            newsLinkModel.setPreLink("/news/NewsDetail?newsId="+newsSort[0].getId());
            newsLinkModel.setPreTheme(newsSort[0].getTheme());
            newsLinkModel.setPostLink("javascript:void(0);");
            newsLinkModel.setPostTheme("无");
        }
        return newsLinkModel;
    }


    @Override
    public int addContent(NewsAddParam newsContentParam) {
        Long newsId= IdGenerator.nextId();
        NewsParam newsParam = new NewsParam();
        String str = String.valueOf(newsId);
        String substring = str.substring(str.length() - 5);
        Long aLong = Long.valueOf(substring);
        newsParam.setNewsId(aLong);
        newsParam.setIntroduce(newsContentParam.getIntroduce());
        newsParam.setTheme(newsContentParam.getTheme());
        newsParam.setPic(newsContentParam.getPic());
        newsParam.setContent(newsContentParam.getContent());
        int count= newsContentMapper.addContent(newsParam);
        if(count>0){
            NewsBrieflyInitial newsBrieflyInitial=new NewsBrieflyInitial();
            newsBrieflyInitial.setId(aLong);
            newsBrieflyInitial.setTheme(newsContentParam.getTheme());
            newsBrieflyInitial.setCreatedTime(new Date());
            UmsAdmin umsAdmin=umsAdminCacheService.getAdmin(newsContentParam.getUserName());
            newsBrieflyInitial.setNewsType(newsContentParam.getNewsType());
            newsBrieflyInitial.setCreatedAdminId(umsAdmin.getId());
            newsBrieflyInitial.setCreatedAdminName(umsAdmin.getNickName());
            return newsMapper.add(newsBrieflyInitial);
        }
        return 0;
    }

    @Override
    public int updateContent(NewsParam newsContentParam) {
        int count=newsContentMapper.updateContent(newsContentParam);
        if(count>0){
            NewsBrieflyInitial newsBrieflyInitial=new NewsBrieflyInitial();
            newsBrieflyInitial.setId(newsContentParam.getNewsId());
            newsBrieflyInitial.setNewsType(newsContentParam.getNewsType());
            newsBrieflyInitial.setTheme(newsContentParam.getTheme());
            return newsMapper.update(newsBrieflyInitial);
        }
        return 0;
    }
}

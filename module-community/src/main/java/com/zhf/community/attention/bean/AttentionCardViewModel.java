package com.zhf.community.attention.bean;


import com.zhf.common.contract.BaseCustomViewModel;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 */
public class AttentionCardViewModel extends BaseCustomViewModel
{
    public String avatarUrl;
    
    public String issuerName;
    
    public long releaseTime;
    
    public String title;
    
    public String description;
    
    public String coverUrl;

    public String blurredUrl;
    
    public String playUrl;
    
    public String category;
    
    public String authorDescription;

    public int videoId ;
    
    // 点赞
    public int collectionCount;
    
    // 分享
    public int shareCount;
    
    // 评论
    public int replyCount;
    
    // 收藏
    public int realCollectionCount;
    
}

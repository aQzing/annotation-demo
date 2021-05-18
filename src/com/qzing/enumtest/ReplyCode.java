package com.qzing.enumtest;

/**
 * 固定回复语
 */
public enum ReplyCode {
    A000("A000","用户首次关注后","感谢关注！我们专注于自然语言处理、人工智能、深度学习、机器学习等相关技术。评论生成器：都市、玄幻、现言、古言、弹幕，想要什么评论来找我吧~  回复 “都市评论：霸道xx” 获取"),
    A001("A001","用户消息关键字格式匹配不对","我说没听懂，希望你能相信我~"),
    A002("A002","用户关键词超过10字符","评论关键字内容太长，我记不住呢~"),
    A003("A003","用户消息styleid和header提取正确，但评论生成接口返回空","我词穷了，给不了你想要的~"),
    A004("A004","请求评论生成接口失败或者报错(服务器或网路原因)","好累，让我休息一会~");
    public String code;
    public String action;
    public String replyContent;
    ReplyCode(String code,String action,String replyContent){
        this.code = code;
        this.action = action;
        this.replyContent = replyContent;
    }
}

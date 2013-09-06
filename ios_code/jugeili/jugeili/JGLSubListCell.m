//
//  JGLSubListCell.m
//  jugeili
//
//  Created by michael on 13-9-6.
//  Copyright (c) 2013年 jugeili. All rights reserved.
//

#import "JGLSubListCell.h"
#import "SDWebImageManager.h"
#import "UIImageView+WebCache.h"
#import "UIButton+WebCache.h"
#import "BlockUI.h"
#import "BUIControl.h"
@interface JGLSubListCell(){
    UIButton *indicImage;
    UILabel *title;
    UILabel *description;
    UILabel *price;
    UILabel *distance;
}
@property (nonatomic, copy) void(^headClickedBlock)(void) ;
@end

@implementation JGLSubListCell
- (id)initWithFrame:(CGRect)frame{
    if ((self = [super initWithFrame:frame])) {
        [self uiInit];
    }
    return self;
}
- (id)init{
    if ((self = [super init])) {
        [self uiInit];
    }
    return self;
}
- (id)initWithCoder:(NSCoder *)aDecoder{
    if ((self = [super initWithCoder:aDecoder])) {
        [self uiInit];
    }
    return self;
}
- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        [self uiInit];
    }
    return self;
}

+ (float)heightWithDataObje:(id)item{
    return 80.0;
}
- (void)uiInit{
    self.frame = CGRectMake(0, 0, 320, 80);
    
    indicImage = [[UIButton alloc] initWithFrame:CGRectMake(10, 10, 80, 60)];
    indicImage.autoresizingMask = UIViewAutoresizingFlexibleHeight | UIViewAutoresizingFlexibleRightMargin;
    indicImage.contentMode = UIViewContentModeScaleAspectFill;
    JGLSubListCell __weak *pself = self;
    [indicImage handleControlEvent:UIControlEventTouchUpInside withBlock:^(id sender) {
        if (pself.headClickedBlock) {
            pself.headClickedBlock();
        }
    }];
    [self addSubview:indicImage];
    
    title = [[UILabel alloc] initWithFrame:CGRectMake(100, indicImage.frame.origin.y, 210, 20)];
    title.font = [UIFont boldSystemFontOfSize:20];
    title.textColor = [UIColor blackColor];
    title.backgroundColor = [UIColor clearColor];
    title.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleBottomMargin;
    [self addSubview:title];
    
    
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated
{
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}
- (void)setItem:(id)item{
    title.text = @"asdafsdasasdas";
    [indicImage setImageWithURL:[NSURL URLWithString:@"http://ww2.sinaimg.cn/bmiddle/6376083cjw1e8cuj98r8hj20f00760ti.jpg"] placeholderImage:[UIImage imageNamed:@"second.png"]];
}
- (void)setHeadCallback:(void(^)(void))headClicked{
    self.headClickedBlock = headClicked;
}
@end

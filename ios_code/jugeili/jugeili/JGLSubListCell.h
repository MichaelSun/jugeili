//
//  JGLSubListCell.h
//  jugeili
//
//  Created by michael on 13-9-6.
//  Copyright (c) 2013年 jugeili. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface JGLSubListCell : UITableViewCell
+ (float)heightWithDataObje:(id)item;
- (void)setItem:(id)item;
- (void)setHeadCallback:(void(^)(void))headClicked;
@end

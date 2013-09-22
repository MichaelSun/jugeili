//
//  JGLAppDelegate.m
//  jugeili
//
//  Created by michael on 13-9-5.
//  Copyright (c) 2013年 jugeili. All rights reserved.
//

#import "JGLAppDelegate.h"
#import "JGLSubListViewController.h"
#import "JGLFirstViewController.h"

@implementation JGLAppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    // Override point for customization after application launch.
    UITabBarController *tab = [UITabBarController new];
    
    JGLFirstViewController *first = [JGLFirstViewController new];
    UINavigationController *navFirst = [[UINavigationController alloc] initWithRootViewController:first];
    
    JGLSubListViewController *list = [JGLSubListViewController new];
    UINavigationController *naviList = [[UINavigationController alloc] initWithRootViewController:list];
    
    tab.viewControllers = @[navFirst, naviList];
    self.window.rootViewController = tab;
    return YES;
}
							
- (void)applicationWillResignActive:(UIApplication *)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

@end

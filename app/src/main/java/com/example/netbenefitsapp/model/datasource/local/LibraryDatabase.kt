package com.example.netbenefitsapp.model.datasource.local

import com.example.netbenefitsapp.model.Article
import com.example.netbenefitsapp.model.Photo
import com.example.netbenefitsapp.model.Video

class LibraryDatabase {

    private lateinit var articles : ArrayList<Article>
    private lateinit var videos : ArrayList<Video>
    private lateinit var photos : ArrayList<Photo>

    fun populateArticles() {
        articles = ArrayList()
        articles.add(Article("The Guide to Diversification", "Build a solid investment strategy to help realize your goals.", "https://www.fidelity.com/viewpoints/investing-ideas/guide-to-diversification"))
        articles.add(Article("Avoid These Big Medicare Mistakes People Make", "Many people believe Medicare covers all retirement medical expenses. It doesn't.", "https://www.forbes.com/sites/nextavenue/2018/07/10/avoid-these-big-medicare-mistakes-people-make/#5ec083639d10"))
        articles.add(Article("How Social Security Survivor Benefits Work", "The benefits provide income for the family of workers who are deceased", "https://www.aarp.org/retirement/social-security/questions-answers/how-do-survivor-benefits-work/"))
        articles.add(Article("Saving for a Loved One With a disability", "ABLE accounts offer a tax-advantaged way to save money.", "https://www.fidelity.com/viewpoints/personal-finance/special-needs-savings"))
        articles.add(Article("How to pay off debt--and save too", "Balancing paying off debt and saving can be tricky. Here's a step-by-step guide.", "https://www.fidelity.com/viewpoints/personal-finance/how-to-pay-off-debt"))
        articles.add(Article("How to save for an emergency", "Ask yourself 4 questions to help prepare for the unexpected.", "https://www.fidelity.com/viewpoints/personal-finance/save-for-an-emergency"))
        articles.add(Article("The ABCs of 529 Savings Plans", "Learn ways to explore investment options and potential tax advantages.", "https://www.fidelity.com/mymoneybasics/abcs-of-529s"))
        articles.add(Article("4 Questions to Ask About Life Insurance", "Feel confident about protecting your family's financial plan", "https://www.fidelity.com/viewpoints/wealth-management/things-to-know-about-life"))
        articles.add(Article("Things to know before taking a 401(k) loan", "Keep this considerations in mind if you need to take a loan from your 401(k)", "https://www.fidelity.com/viewpoints/financial-basics/avoiding-401k-loans"))
        articles.add(Article("Your bridge to Medicare", "Explore 4 health care coverage options as you transition to Medicare at age 65.", "https://www.fidelity.com/viewpoints/retirement/transition-to-medicare"))
    }

    fun populateVideos() {
        videos = ArrayList()
        videos.add(Video("Split Decisions: Student Debt or Retirement", "Decisions aren't always easy to make—particularly when your money's involved. With money guru Jean Chatzky, we'll help you weigh the pros and cons before you make your move.", "https://www.fidelity.com/mymoneybasics/student-debt-or-retirement"))
        videos.add(Video("Roth IRA or traditional IRA?", "Decisions aren't always easy to make—particularly when your money's involved. With money guru Jean Chatzky, we'll help you weigh the pros and cons before you make your move.", "https://www.fidelity.com/mymoneylifestyle/roth-ira-or-traditional-ira"))
        videos.add(Video("Buy a car or lease it?", "When it comes to your ride is it better to buy or lease?", "https://www.fidelity.com/mymoneybasics/buy-a-car-or-lease-it"))
        videos.add(Video("How to prioritize your debt", "How to tackle the many different types of debt to get rid of it fast.", "https://www.fidelity.com/mymoneybasics/how-to-prioritize-debt"))
        videos.add(Video("Real Stories: Meet Marty", "Marty shares her lessons learned about saving for the future to help you successfully plan for yours.", "https://www.fidelity.com/mymoneylifestyle/meet-marty"))
        videos.add(Video("Real stories: Meet Robin and Doff", "Learn from one couple's experience on making three big decisions before retiring.", "https://www.fidelity.com/mymoneylifestyle/meet-robin-and-doff"))
        videos.add(Video("Real Stories: Meet Susan", "Susan shares her lessons learned about saving for the future to help you successfully plan for yours.", "https://www.fidelity.com/mymoneybasics/meet-susan"))
        videos.add(Video("3 Things to Know Before Taking a Loan", "Before dipping into your long-term retirement account for some quick cash, here are three things you should know.", "https://www.fidelity.com/mymoneybasics/3-things-you-should-know-before-taking-a-retirement-loan"))
    }

    fun populatePhotos() {
        photos = ArrayList()
        photos.add(Photo("Arya", "https://i.imgur.com/w0hkvWT.jpg"))
        photos.add(Photo("Sansa", "https://i.imgur.com/B7FMl10.jpg"))
        photos.add(Photo("Jon", "https://i.imgur.com/IEfdxBD.jpg"))
        photos.add(Photo("Daenerys", "https://i.imgur.com/HynQP07.jpg"))
    }

    fun getArticles() : ArrayList<Article> {
        return articles
    }

    fun getVideos() : ArrayList<Video> {
        return videos
    }

    fun getPhotos() : ArrayList<Photo> {
        return photos
    }
}
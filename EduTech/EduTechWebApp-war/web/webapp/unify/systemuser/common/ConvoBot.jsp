<%-- 
  - Author(s):          TAN CHIN WEE WINSTON
  - Date:               6th April 2018
  - Version:            1.0
  - Credits to:         convoForm
  - Description:        Unify Chatbot
  --%>
  
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Unify Chatbot</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/unify/systemuser/baselayout/convo/jquery.convform.css">
        <link rel="stylesheet" href="css/unify/systemuser/baselayout/convo/bootstrap-v3.3.7.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        
        <style type="text/css">
            * { font-family: 'Roboto', sans-serif; }
            html, body { font-size: 13px; }
            .card {
                background: #fff;
                box-shadow: 13px 13px 28px 2px rgba(0, 0, 0, 0.035);
                padding: 7px 35px 7px 5px;
            }
        </style>
    </head>
    <body>
        <section id="demo">
            <div class="vertical-align">
                <div class="card no-border">
                    <div id="chat" class="conv-form-wrapper">
                        <form action="" method="GET" class="hidden">
                            <select name="chatBotMenu" conv-question="Good day to you! How may I help?">
                                <option value="navigate">Cannot Find A Page</option>
                                <option value="sure">Report A Problem</option>
                            </select>
                            <div conv-fork="chatBotMenu">
                                <div conv-case="navigate">
                                    <select name="moduleOption" conv-question="Select one of the following where your page may be in.">
                                        <option value="marketplace">Marketplace</option>
                                        <option value="errands">Errands</option>
                                        <option value="reviews">Company Reviews</option>
                                        <option value="shouts">Shouts</option>
                                    </select>
                                </div>
                                <div conv-case="sure">
                                    <select name="reportOption" conv-question="What would you like to report about?">
                                        <option value="inappropriateListing">Inappropriate Listing</option>
                                        <option value="offensiveContent">Offensive Content</option>
                                        <option value="irrelevantKeywords">Irrelevant Keywords</option>
                                    </select>
                                </div>
                            </div>
                            <div conv-fork="moduleOption">
                                <div conv-case="marketplace">
                                    <select name="marketplaceOption" conv-question="Select one of the following marketplace page to navigate to.">
                                        <option value="mktListings" callback="mktListings">Marketplace Listings</option>
                                        <option value="mktListingsNearMe" callback="mktListingsNearMe">Marketplace Listings Near Me</option>
                                        <option value="myMktListings" callback="myMktListings">My Marketplace Listings</option>
                                    </select>
                                </div>
                                <div conv-case="errands">
                                    <select name="errandsOption" conv-question="Select one of the following jobs page to navigate to.">
                                        <option value="jobListings" callback="jobListings">Job Listings</option>
                                        <option value="myJobListings" callback="myJobListings">My Job Listings</option>
                                    </select>
                                </div>
                                <div conv-case="reviews">
                                    <select name="reviewsOption" conv-question="Select one of the following reviews page to navigate to.">
                                        <option value="companyListings" callback="companyListings">Company Listings</option>
                                        <option value="myCompanyReviews" callback="myCompanyReviews">My Company Reviews</option>
                                    </select>
                                </div>
                                <div conv-case="shouts">
                                    <select name="shoutsOption" conv-question="Select one of the following shouts page to navigate to.">
                                        <option value="shoutsListings" callback="shoutsListings">Shouts Listings</option>
                                        <option value="myShouts" callback="myShouts">My Shouts</option>
                                    </select>
                                </div>
                            </div>
                            <div conv-fork="reportOption">
                                <input type="text" name="name" conv-question="What is the issue that you want to report about?">
                            </div>
                            <select conv-question="This is it! Would like to requery?">
                                <option value="yes" callback="rollback">Yes</option>
                                <option value="no" callback="restore">No</option>
                            </select>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <script src="js/unify/systemuser/basejs/convo/jquery-1.12.3.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/convo/autosize.min.js" type="text/javascript"></script>
        <script src="js/unify/systemuser/basejs/convo/jquery.convform.js" type="text/javascript"></script>

        <script type="text/javascript">
            function mktListings() { window.open("MarketplaceSysUser?pageTransit=goToViewItemListingSYS", "_parent"); }
            function mktListingsNearMe() { window.open("MarketplaceSysUser?pageTransit=goToProximityItemListingSYS", "_parent"); }
            function myMktListings() { window.open("ProfileSysUser?pageTransit=goToUnifyUserAccountSYS", "_parent"); }
            function jobListings() { window.open("ErrandsSysUser?pageTransit=goToViewJobListingSYS", "_parent"); }
            function myJobListings() { window.open("ProfileSysUser?pageTransit=goToMyJobListing", "_parent"); }
            function companyListings() { window.open("VoicesSysUser?pageTransit=goToViewCompanyListingSYS", "_parent"); }
            function myCompanyReviews() { window.open("ProfileSysUser?pageTransit=goToCompanyReview", "_parent"); }
            function shoutsListings() { window.open("MarketplaceSysUser?pageTransit=goToViewItemListingSYS", "_parent"); }
            function myShouts() { window.open("ProfileSysUser?pageTransit=goToUnifyUserAccountSYS", "_parent"); }
            
            var rollbackTo = false;
            var originalState = false;
            function storeState(stateWrapper) {
                rollbackTo = stateWrapper.current;
                console.log("storeState called: ", rollbackTo);
            }
            function rollback(stateWrapper) {
                console.log("rollback called: ", rollbackTo, originalState);
                console.log("answers at the time of user input: ", stateWrapper.answers);
                if (rollbackTo != false) {
                    if (originalState == false) {
                        originalState = stateWrapper.current.next;
                        console.log('stored original state');
                    }
                    stateWrapper.current.next = rollbackTo;
                    console.log('changed current.next to rollbackTo');
                }
            }
            function restore(stateWrapper) {
                if (originalState != false) {
                    stateWrapper.current.next = originalState;
                    console.log('changed current.next to originalState');
                }
            }
        </script>
    </body>
</html>

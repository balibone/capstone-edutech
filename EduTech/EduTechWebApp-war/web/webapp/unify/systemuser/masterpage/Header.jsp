<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <nav class="navbar navbar-expand-md bg-primary navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="UserProfile?pageTransit=goToUserProfile"><i class="fa d-inline fa-2x fa-graduation-cap"></i>
                    <h4 class="d-inline-flex">Unify</h4>
                </a>
                <div class="collapse navbar-collapse text-center justify-content-center" id="navbar2SupportedContent">
                    <ul class="navbar-nav">
                        <li class="nav-item bg-primary justify-content-center align-items-center align-self-center">
                            <a class="nav-link text-white" href="MarketplaceSysUser?pageTransit=goToViewItemListingSYS"><i class="fa d-inline fa-lg fa-shopping-basket"></i> Marketplace (Trade)</a>
                        </li>
                        <li class="nav-item text-capitalize bg-primary text-center justify-content-center flex-row align-items-center align-self-center">
                            <a class="nav-link text-white" href="ErrandsSysUser?pageTransit=goToJobListing"><i class="fa d-inline fa-lg fa-handshake-o"></i> Errands (Help)</a>
                        </li>
                        <li class="nav-item text-capitalize bg-primary text-center justify-content-center flex-row align-items-center align-self-center">
                            <a class="nav-link text-white" href="VoicesSysUser?pageTransit=goToReviewListing"><i class="fa d-inline fa-lg fa-comments-o" aria-hidden="true"></i> Voices (Review)</a>
                        </li>
                        <li class="nav-item text-capitalize bg-primary text-center justify-content-center flex-row align-items-center align-self-center">
                            <a class="nav-link text-white" href="../explore/explore.html"><i class="fa d-inline fa-lg fa-map"></i> Explore (Map)</a>
                        </li>
                    </ul>
                </div>
                <a href="../dashboard/messages.html" class="btn btn-primary d-inline"><i class="fa d-inline fa-lg fa-envelope" aria-hidden="true"></i>
                    Messages <span class="badge badge-light">4</span>
                </a>
                <a href="CommonInfra?pageTransit=goToLogout" class="btn btn-primary d-inline" ><i class="fa d-inline fa-lg fa-user-o" aria-hidden="true"></i>
                    Sign Out
                </a>
            </div>
        </nav>
    </body>
</html>
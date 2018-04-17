<%@include file="/webapp/commoninfrastructure/SessionCheck.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head></head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light p-0 d-none d-lg-flex navbar-theme">
            <div class="container">
                <div class="pos-r d-flex w-100">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item"><a class="nav-link" href="ProfileSysUser?pageTransit=goToUnifyUserAccountSYS"><i class="fa fa-fw fa-home"></i>&nbsp;Unify Home</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="marketplaceNAV" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-fw fa-shopping-cart"></i>&nbsp;Marketplace</a>
                            <div class="dropdown-menu t-90 smooth" aria-labelledby="marketplaceNAV">
                                <a class="dropdown-item" href="MarketplaceSysUser?pageTransit=goToViewItemListingSYS"><i class="fa fa-fw fa-shopping-cart"></i>&nbsp;Item Listing</a>
                                <a class="dropdown-item" href="MarketplaceSysUser?pageTransit=goToProximityItemListingSYS"><i class="fa fa-fw fa-shopping-cart"></i>&nbsp;Item Listing Around Me</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="errandsNAV" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-fw fa-suitcase"></i>&nbsp;Errands</a>
                            <div class="dropdown-menu t-90 smooth" aria-labelledby="errandsNAV">
                                <a class="dropdown-item" href="ErrandsSysUser?pageTransit=goToViewJobListingSYS"><i class="fa fa-fw fa-suitcase"></i>&nbsp;Errands Listing</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="reviewsNAV" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-fw fa-comment"></i>&nbsp;Company Review</a>
                            <div class="dropdown-menu t-90 smooth" aria-labelledby="reviewsNAV">
                                <a class="dropdown-item" href="VoicesSysUser?pageTransit=goToViewCompanyListingSYS"><i class="fa fa-fw fa-comment"></i>&nbsp;Company Listing</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="shoutsNAV" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-fw fa-comments"></i>&nbsp;Shouts</a>
                            <div class="dropdown-menu t-90 smooth" aria-labelledby="shoutsNAV">
                                <a class="dropdown-item" href="ShoutsSysUser?pageTransit=goToViewShoutsListingSYS"><i class="fa fa-fw fa-comments"></i>&nbsp;Shouts Listing</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="eventsNAV" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-fw fa-calendar"></i>&nbsp;Events</a>
                            <div class="dropdown-menu t-90 smooth" aria-labelledby="eventsNAV">
                                <a class="dropdown-item" href="EventsSysUser?pageTransit=goToViewEventsListingSYS"><i class="fa fa-fw fa-calendar"></i>&nbsp;Events Listing</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="myAccountNav" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-fw fa-user-circle"></i>&nbsp;My Account</a>
                            <div class="dropdown-menu t-90 smooth" aria-labelledby="myAccountNav">
                                <a class="dropdown-item marketplaceBtn" data-toggle="modal" data-target="#marketplaceModal"><i class="fa fa-fw fa-shopping-cart"></i>&nbsp;My Marketplace</a>
                                <a class="dropdown-item" data-toggle="modal" data-target="#errandsModalCenter"><i class="fa fa-fw fa-suitcase"></i>&nbsp;My Errands</a>
                                <a class="dropdown-item shoutBtn"><i class="fa fa-fw fa-comments"></i>&nbsp;My Whispers</a>
                                <a class="dropdown-item eventBtn"><i class="fa fa-fw fa-calendar"></i>&nbsp;My Events</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

    </body>
</html>

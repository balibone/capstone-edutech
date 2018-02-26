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
                        <li class="nav-item"><a class="nav-link" href="index.html">Unify Home</a></li>
                        <li class="nav-item dropdown active">
                            <a class="nav-link dropdown-toggle" href="#" id="marketplaceNAV" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Marketplace</a>
                            <div class="dropdown-menu t-90 smooth" aria-labelledby="marketplaceNAV">
                                <a class="dropdown-item" href="MarketplaceSysUser?pageTransit=goToViewItemListingSYS">Item Listing</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="errandsNAV" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Errands</a>
                            <div class="dropdown-menu t-90 smooth" aria-labelledby="errandsNAV">
                                <a class="dropdown-item" href="ErrandsSysUser?pageTransit=goToViewJobListingSYS">Errands Listing</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="reviewsNAV" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Company Review</a>
                            <div class="dropdown-menu t-90 smooth" aria-labelledby="reviewsNAV">
                                <a class="dropdown-item" href="about.html">Company Listing</a>
                            </div>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="venuemap.html">Venue Map</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </body>
</html>

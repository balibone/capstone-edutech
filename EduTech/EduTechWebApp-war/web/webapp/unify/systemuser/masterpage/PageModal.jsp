<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            .cursorPointer { cursor: pointer; }
        </style>
    </head>
    <body>
        <div class="modal fade" id="marketplaceModal" tabindex="-1" role="dialog" aria-hidden="true" style="width:calc(75%); margin: 0 auto;">
            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Select one of the following to view.</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="card text-center mb-3 bg-light text-dark cursorPointer" onclick="window.location = 'ProfileSysUser?pageTransit=goToUnifyUserAccountSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-shopping-cart display-2"></i></h1>
                                        <h6>My Listings</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="card text-center mb-3 bg-light text-dark cursorPointer" onclick="window.location = 'ProfileSysUser?pageTransit=goToMarketplaceTransSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-book display-2"></i></h1>
                                        <h6>My Transactions</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="card text-center mb-3 bg-light text-dark cursorPointer" onclick="window.location = 'ProfileSysUser?pageTransit=goToUserItemWishlistSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-heart display-2"></i></h1>
                                        <h6>My Wishlist</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="card text-center mb-3 bg-light text-dark cursorPointer" onclick="window.location = 'ProfileSysUser?pageTransit=goToMyBuyerOfferListSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-edit display-2"></i></h1>
                                        <h6>My Offers</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <div class="card text-center mb-3 bg-light text-dark cursorPointer" onclick="window.location = 'ProfileSysUser?pageTransit=goToPendingItemOfferListSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-bullhorn display-2"></i></h1>
                                        <h6>Marketplace Offers</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Shouts Modal-->
        <div class="modal fade" id="shoutsModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" style="width: 1200px;">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle" style="font-size: 15px"><strong>Select one of the following to view.</strong></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ShoutsSysUser?pageTransit=goToViewMyShoutsListingSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-fw fa-bullhorn"></i></h1>
                                        <h6>My Shouts</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ShoutsSysUser?pageTransit=goToViewMyBookmarkedShoutsListingSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-fw fa-bookmark"></i></h1>
                                        <h6>My Bookmarked Shouts</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End of Shouts Modal-->

        <!-- Events Modal-->
        <div class="modal fade" id="eventsModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" style="width: 1200px;">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle" style="font-size: 15px"><strong>Select one of the following to view.</strong></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'EventsSysUser?pageTransit=goToViewMyRequestedEventsListingSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-fw fa-calendar-plus-o"></i></h1>
                                        <h6>My Requested Events (Pending Approval)</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'EventsSysUser?pageTransit=goToViewMyEventsListingSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-fw fa-calendar"></i></h1>
                                        <h6>My Events</h6>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-5 ml-4">
                                <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'EventsSysUser?pageTransit=goToViewMyRsvpEventsListingSYS';">
                                    <div class="card-block card-title mt-5 mb-5">
                                        <h1 class="mb-3"><i class="fa fa-fw fa-ticket"></i></h1>
                                        <h6>My RSVP'ed Events</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End of Events Modal-->
        <div class="modal fade" id="voicesModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" style="width: 1200px;">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle" style="font-size: 15px"><strong>Select one of the following to view.</strong></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-sm-5 ml-4">
                                    <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ProfileSysUser?pageTransit=goToCompanyReview';">
                                        <div class="card-block card-title mt-5 mb-5">
                                            <h1 class="mb-3"><i class="fa fa-fw fa-building"></i></h1>
                                            <h6>My Company Reviews</h6>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-5 ml-4">
                                    <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ProfileSysUser?pageTransit=goToCompanyRequest';">
                                        <div class="card-block card-title mt-5 mb-5">
                                            <h1 class="mb-3"><i class="fa fa-fw fa-question-circle"></i></h1>
                                            <h6>My Company Requests</h6>
                                        </div>
                                    </div>
                                </div>
                            
                                <div class="col-sm-5 ml-4">
                                    <div class="card text-center mb-3 bg-light text-dark" onclick="window.location = 'ProfileSysUser?pageTransit=goToResume';">
                                        <div class="card-block card-title mt-5 mb-5">
                                            <h1 class="mb-3"><i class="fa fa-fw fa-file"></i></h1>
                                            <h6>My Resume List</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>

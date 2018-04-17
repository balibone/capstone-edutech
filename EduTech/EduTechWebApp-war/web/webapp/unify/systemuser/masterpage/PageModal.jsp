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
    </body>
</html>

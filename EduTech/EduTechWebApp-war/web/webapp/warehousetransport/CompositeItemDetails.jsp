<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SG MapleStore - Composite Item Details</title>
        
        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/commoninfrastructure/baselayout/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css/warehousetransport/weblayout/CompositeItemDetailsCSS.css" rel="stylesheet" type="text/css">
        
        <script src="js/commoninfrastructure/basejs/jquery.min.js" type="text/javascript"></script>
        <script src="js/warehousetransport/webjs/CompositeItemDetailsJS.js" type="text/javascript"></script>
    </head>
    <body>
        <%
            Vector compositeItemInfoArr = (Vector)request.getAttribute("compositeItemInfo");
            
            String compositeImagePath, compositeName, compositeDescription, compositeSellPrice, compositeQuantity;
            compositeImagePath = compositeName = compositeDescription = compositeSellPrice = compositeQuantity = "";
            ArrayList<Vector> itemListInCompositeArr = new ArrayList<Vector>();
            
            if (compositeItemInfoArr != null) {
                compositeImagePath = (String)compositeItemInfoArr.get(0);
                compositeName = (String)compositeItemInfoArr.get(1);
                compositeDescription = (String)compositeItemInfoArr.get(2);
                compositeSellPrice = (String.valueOf(compositeItemInfoArr.get(3)));
                compositeQuantity = (String.valueOf(compositeItemInfoArr.get(4)));
                itemListInCompositeArr = (ArrayList)compositeItemInfoArr.get(5);
                
                System.out.println("Servlet Name: " + compositeName);
                System.out.println("Servlet Selling Price: " + compositeSellPrice);
            }
        %>
        <div class="row" style="visibility: visible; margin: 30px 50px 0 50px; background-color: #fff;">
            <div class="col-sm-5 col-md-5 gallery-holder">
                <div class="single-product-gallery">
                    <div class="owl-item" style="width: 336px;">
                        <div class="single-product-gallery-item">
                            <img src="uploads/images/warehouse/CompositeItems/<%= compositeImagePath %>" style="max-width: 251px; min-width: 251px; max-height: 256px; min-height: 256px;" />
                        </div>
                    </div>
                </div>
            </div>   			
            <div class="col-sm-7 col-md-7 product-info-block">
                <div class="product-info">
                    <h1 class="name"><%= compositeName %></h1>
                    <div class="stock-container">
                        <div class="col-sm-3">
                            <div class="stock-box">
                                <span class="label">Current Quantity :</span>
                            </div>	
                        </div>
                        <div class="col-sm-9">
                            <div class="stock-box">
                                <span class="value">
                                    <input type="text" readonly="readonly" id="compositeQuantity" value="<%= compositeQuantity %>" style="border: none;" />
                                </span>
                            </div>	
                        </div>
                    </div>
                    <div class="description-container m-t-20">
                        <%= compositeDescription %><br/><br/>This package consists of:
                        <ul class="setLeftUL">
                        <%
                            if(itemListInCompositeArr.isEmpty()){
                        %>
                            <li>INVALID COMPOSITE PACKAGE</li>
                        <%
                            }
                            else {
                                for(int i = 0; i <= itemListInCompositeArr.size()-1; i++){
                                    Vector v = itemListInCompositeArr.get(i);
                                    String itemNameInComposite = String.valueOf(v.get(0));
                                    String itemSKUInComposite = String.valueOf(v.get(1));
                                    String itemQtyInComposite = String.valueOf(v.get(2));
                        %>
                            <li><strong>x<%= itemQtyInComposite %></strong>&nbsp;-&nbsp;<%= itemNameInComposite %>&nbsp;(SKU:&nbsp;<%= itemSKUInComposite %>)</li>
                        <%      }   %>
                        <%  }   %>
                        </ul>
                    </div>
                    <div class="price-container m-t-20">
                        <div class="col-sm-6">
                            <div class="price-box"><span class="price">$<%= compositeSellPrice %></span></div>
                        </div>
                    </div>

                    <div class="quantity-container">
                        <div class="col-sm-3"><span class="label">New Quantity :</span></div>
                        <div class="col-sm-2">
                            <div class="quant-input">
                                <input type="text" id="newCompositeQuantity" size="8" />
                            </div>
                        </div>
                        <div class="col-sm-2">&nbsp;</div>
                        <div class="col-sm-5">
                            <button type="button" class="btn btn-primary" id="updateCompositeQty">
                                <i class="fa fa-pencil-square-o" style="padding-right: 10px;"></i>Update Quantity
                            </button>
                        </div>
                    </div>
                    <div class="errorMessage"></div>
                </div>
            </div>
        </div>
        <div class="row" style="visibility: visible; margin: 25px 50px 0 50px; background-color: #fff;">
            <h5>Current inventory level for items associated with this package:</h5>
            <table class="line-item-table" id="assocItemInventoryTable">
                <thead>
                    <tr class="line-item-header zb-txn-form invoice-nodiscount line-item-us">
                        <th colspan="2" class="line-item-column over-flow item-details">Item Details</th>
                        <th class="line-item-column over-flow item-qty">SKU</th>
                        <th class="line-item-column over-flow item-qty">Quantity Available</th>
                        <th class="line-item-column over-flow item-qty">Selling Price</th>
                    </tr>
                </thead>
                <tbody class="line-item-body invoice-nodiscount line-item-us">
                    <%
                        ArrayList<Vector> assocCompItemInventoryList = (ArrayList)request.getAttribute("assocCompItemInventoryList");
                        if(assocCompItemInventoryList.isEmpty()){
                    %>
                    <tr>
                        <td colspan="5" style="text-align: center;">There are no associated inventory records available.</td>
                    </tr>
                    <%
                        }
                        else {
                            for(int i = 0; i <= assocCompItemInventoryList.size()-1; i++){
                                Vector v = assocCompItemInventoryList.get(i);
                                String assocItemInventoryPath = String.valueOf(v.get(0));
                                String assocItemInventoryName = String.valueOf(v.get(1));
                                String assocItemInventorySKU = String.valueOf(v.get(2));
                                String assocItemInventoryQuantity = String.valueOf(v.get(3));
                                String assocItemInventorySellPrice = String.valueOf(v.get(4));
                    %>
                    <tr class="line-item new-line-item">
                        <td class="line-item-column item-img"><span class="displayImage"><img src="uploads/images/warehouse/Items/<%= assocItemInventoryPath %>" /></span></td>
                        <td class="line-item-column item-details"><span class="displayField"><%= assocItemInventoryName %></span></td>
                        <td class="line-item-column item-qty text-muted"><span class="displayField"><%= assocItemInventorySKU %></span></td>
                        <td class="line-item-column item-qty text-muted"><span class="displayField"><%= assocItemInventoryQuantity %></span></td>
                        <td class="line-item-column item-qty text-muted"><span class="displayField"><%= assocItemInventorySellPrice %></span></td>
                    </tr>
                    <%      }   %>
                    <%  }   %>
                </tbody>
            </table>
        </div>
    </body>
</html>
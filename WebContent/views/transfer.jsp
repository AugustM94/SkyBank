
<div id="page-wrapper" ng-class="{'open': toggle}" ng-cloak>
	<!-- Sidebar -->
	<%@include file="./sidebar.jsp" %>
	<!-- End Sidebar -->
	<div class="row header">
		<div class="col-xs-12">
			<div class="meta" style="margin-bottom:20px;">
				<div class="page">Dashboard</div>
				<div class="breadcrumb-links">Home</div>
			</div>
		</div>
	<div class="row">
			<form novalidate class="simple-form">
				ReceiverId: <input type="text" ng-model="receiver" /><br /> 
				Amount: <input type="number" ng-model="amount" /><br /> 
				<input type="button" ng-click="reset()" value="Reset" /> <input
					type="submit" ng-click="transferFunds(2,receiver,amount)" value="Save" />
			</form>
		</div>
</div>
<!-- End Page Content -->

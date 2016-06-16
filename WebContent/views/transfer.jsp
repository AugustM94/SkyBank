
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
		<div class="form-group col-lg-6 col-md-6 col-xs-6">
		<form novalidate class="simple-form">
					<label for="receiver">Receiver</label>
					<input type="text" name="receiver" class="form-control" ng-model="receiver" required/><br /> 
					
					<label for="amount">Amount</label>
					<input type="number" name="amount" class="form-control" ng-model="amount" /><br /> 

					<div class="form-actions">
						<button type="button" ng-click="reset()" class="btn btn-default">Reset</button> 
						<button type="submit" ng-click="transferFunds(2,receiver,amount)" ng-disabled="form.$invalid" class="btn btn-default">Transfer</button>
				    </div>
				</form>
		</div>	
	</div>
</div>
<!-- End Page Content -->

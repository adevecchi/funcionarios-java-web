<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../includes/header.jsp" />
    <div class="container">
        <nav class="mt-2" aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item active" aria-current="page">Funcionários</li>
			</ol>
		</nav>
        
        <div class="mt-2 mb-3">
            <button type="button" class="btn btn-primary btn-add" data-toggle="modal" data-target="#modal-add-upd">
            	<i class="fa fa-plus" aria-hidden="true"></i> Adicionar
            </button>
        </div>
        
        <table class="table table-hover">
        	<thead>
            	<tr>
                	<th>#</th>
                    <th>Primeiro Nome</th>
                    <th>Último Nome</th>
                    <th>Departamento</th>
                    <th>Email</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    
    <div class="modal fade" id="modal-details" tabindex="-1" aria-labelledby="detailsLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Detalhes</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="card mb-3" style="width: 90%; max-width: 90%; margin: 0 auto;">
						<div class="card-header text-white bg-dark" id="details-header">Funcionário</div>
						<div class="row no-gutters">
							<div class="col-3 border-right" style="text-align:center;">
								<img id="details-img" src="" class="img-thumbnail border-0" style="height: 200px;">
							</div>
							<div class="col-9">
								<div class="card-body">
									<p class="card-text"><span class="font-weight-bold">Nome:</span> <span id="details-name"></span></p>
									<p class="card-text"><span class="font-weight-bold">ID Registro:</span> <span id="details-id"></span></p>
									<p class="card-text"><span class="font-weight-bold">Departamento:</span> <span id="details-dpt"></span></p>
									<p class="card-text"><span class="font-weight-bold">E-mail:</span> <span id="details-email"></span></p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>
    
    <div class="modal fade" id="modal-add-upd" tabindex="-1" aria-labelledby="addAndUpdatelLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="title-add-upd"></h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form>
					<div class="modal-body">
						<div class="form-group mb-0">
							<label for="primeiro-nome" class="col-form-label">Primeiro Nome:</label>
							<input type="text" class="form-control" id="primeiro-nome" name="firstName" required>
						</div>
						<div class="form-group mb-0">
							<label for="ultimo-nome" class="col-form-label">Último Nome:</label>
							<input type="text" class="form-control" id="ultimo-nome" name="lastName" required>
						</div>
						<div class="form-group mb-0">
							<label for="departamento" class="col-form-label">Departamento:</label>
							<select class="custom-select" id="departamento" name="department" required>
								<option value="Desenvolvimento">Desenvolvimento</option>
								<option value="Qualidade">Qualidade</option>
								<option value="Infraestrutura">Infraestrutura</option>
								<option value="Marketing">Marketing</option>
							</select>
						</div>
						<div class="form-group mb-0">
							<label for="email" class="col-form-label">E-mail:</label>
							<input type="email" class="form-control" id="email" name="email" required>
						</div>
						<hr style="width:99%;">
						<div class="-modal-body">
							<div class="row">
								<div class="col-9">
									<div class="form-group mb-0" style="padding-top:60px;">
										<div class="custom-file">
											<input type="file" class="custom-file-input" id="employee-photo" name="image" accept="image/*">
											<label class="custom-file-label" for="employee-photo" data-browse="Foto">Selecionar foto</label>
										</div>
									</div>
								</div>
								<div class="col-3">
									<div>
										<img src="assets/image/images.png" id="preview" class="img-thumbnail" style="height:100px;">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary btn-action" data-dismiss="modal">Cancelar</button>
						<button type="submit" class="btn btn-success btn-action btn-save">Salvar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
    
    <div class="modal fade" id="modal-remove" tabindex="-1" aria-labelledby="excluirLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title text-danger"><i class="fa fa-trash-o aria-hidden="true""></i> Excluir</h5>
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body">
	                <p>Deseja excluir o funcionário?</p>
	            </div>
	            <div class="modal-footer">
	                <form id="frm-remove">
	                	<input type="hidden" name="id" value="">
	                    <button type="button" class="btn btn-danger btn-action">Sim</button>
	                </form>
	                <button type="button" class="btn btn-secondary btn-action" data-dismiss="modal">Não</button>
	            </div>
	        </div>
	    </div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" crossorigin></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" crossorigin></script>
	<script src="https://cdn.jsdelivr.net/npm/bs-custom-file-input/dist/bs-custom-file-input.min.js" crossorigin></script>
	<script src="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.js" crossorigin></script>
	<script>
		var path = '<%= request.getContextPath() %>';
	</script>
	<script src="assets/javascript/funcionario.js"></script>
<jsp:include page="../includes/footer.jsp" />
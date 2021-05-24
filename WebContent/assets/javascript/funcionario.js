/**
 * 
 */

(function (path) {
	
	bsCustomFileInput.init();
	
	$('.table').dataTable({
        'language': {
            'emptyTable': 'Nenhum registro encontrado',
            'lengthMenu': 'Exibir _MENU_ resultados por página',
            'search': 'Pesquisar',
            'paginate': { 'previous': 'Anterior', 'next': 'Próximo' },
            'zeroRecords': 'Nenhum registro encontrado',
            'info': 'Mostrando de _START_ até _END_ de _TOTAL_ registros',
            'infoEmpty': 'Mostrando 0 até 0 de 0 registros',
            'infoFiltered': '(Filtrados de _MAX_ registros)',
            'infoThousands': '.',
            'loadingRecords': 'Carregando...',
            'processing': 'Processando...',
            'thousands': '.'
        },
        'processing': true,
        'serverSide': true,
        'destroy': true,
        'order': [[0, 'asc']],
        'lengthMenu': [5, 10, 25, 50, 100],
        'pageLength': 5,
        'ordering': false,
        'ajax': {
            'url': path+'/funcionarios/?cmd=GetEmployees',
            'type': 'POST',
            'datatype': 'json'
        },
        'columns': [
            { 'data': 'id', 'autoWidth': true },
            { 'data': 'firstName', 'autoWidth': true },
            { 'data': 'lastName', 'autoWidth': true },
            { 'data': 'department', 'autoWidth': true },
            { 'data': 'email', 'autoWidth': true },
            {
                'data': 'id',
                'render': function (data) {
                    return '<button type="button" class="btn btn-secondary btn-sm btn-details" data-id="' + data + '">Detalhes</button>';
                },
                'searchable': false,
                'width': '5%'
            },
            {
                'data': 'id',
                'render': function (data) {
                    return '<button type="button" class="btn btn-info btn-sm btn-edit" data-id="' + data + '">Editar</button>';
                },
                'searchable': false,
                'width': '5%'
            },
            {
                'data': 'id',
                'render': function (data) {
                    return '<button type="button" class="btn btn-danger btn-sm btn-remove" data-id="' + data + '" data-toggle="modal" data-target="#modal-remove">Excluir</button>';
                },
                'searchable': false,
                'width': '5%'
            }
        ]
    });
	
	$('.nav-link').removeClass('active');
	$('#menu-funcionarios').addClass('active');
	
	$('#modal-add-upd').on('change', '#employee-photo', function (evt) {
		var reader = new FileReader();
		
		reader.onload = function () {
			var preview = document.getElementById('preview');
			preview.src = reader.result;
		};
		
		reader.readAsDataURL(evt.target.files[0]);
	});
	
	$('.btn-add').on('click', function (evt) {
		var $modal = $('#modal-add-upd');
		
		$('.btn-action').prop('disabled', false);
		
		$modal
			.find('#title-add-upd')
			.html('<i class="fa fa-plus-circle" aria-hidden="true"></i> Novo')
			.addClass('text-primary');
		$modal.find('form').attr('action', path+'/funcionarios/?cmd=New');
	});
	
	$('.table').on('click', '.btn-edit', function (evt) {
		var $modal = $('#modal-add-upd');
		
		$('.btn-action').prop('disabled', false);
		
		$modal
			.find('#title-add-upd')
			.html('<i class="fa fa-pencil-square-o fa-lg" aria-hidden="true"></i> Editar')
			.addClass('text-info');
		$modal.find('form').attr('action', path+'/funcionarios/?cmd=Update');
		
		$.ajax({
            type: 'POST',
            url: path+'/funcionarios/?cmd=Edit',
            data: 'id='+$(this).data('id'),
            contentType: 'application/x-www-form-urlencoded',
            dataType: 'json'
        })
        .done(function (data) {
        	if (data.status) {
        		$('#primeiro-nome').val(data.employee.firstName);
        		$('#ultimo-nome').val(data.employee.lastName);
        		$('#departamento').val(data.employee.department);
        		$('#email').val(data.employee.email);
        		var image = data.employee.image.length == 0 ? 'assets/image/images.png' : 'uploads/'+data.employee.image;
        		$('#preview').attr('src', image);
        		$modal.find('form').append('<input type="hidden" name="id" value="' + data.employee.id + '">');
        		$modal.find('form').append('<input type="hidden" name="old_image" value="' + data.employee.image + '">');
        		$modal.modal('show');
        	}
        })
        .fail(function (jqXHR, textStatus) {
        	console.log(textStatus);
        });
	});
	
	$('#modal-add-upd').on('submit', 'form', function (evt) {
		evt.preventDefault();
		evt.stopPropagation();
		
		var formData = new FormData($('#modal-add-upd').find('form')[0]);
		
		$('.btn-action').prop('disabled', true);
		
		$.ajax({
			type: 'POST',
		    url: $('#modal-add-upd').find('form').attr('action'),
		    data: formData,
		    enctype: 'multipart/form-data',
		    cache: false,
		    contentType: false,
		    processData: false
		})
		.done(function (data) {
			if (data.status) {
				$('#modal-add-upd').modal('hide');
				$('.table').DataTable().draw('page');
			}
		})
		.fail(function (jqXHR, textStatus) {
			console.log(textStatus);
		})
	});
	
	$('#modal-add-upd').on('hidden.bs.modal', function (e) {
		var modalForm = $('#modal-add-upd').find('form')[0];
		
		modalForm.reset();
		$(modalForm).find('#preview').attr('src', 'assets/image/images.png');
	});
	
	$('.table').on('click', '.btn-details', function (evt) {
    	$.ajax({
            type: 'POST',
            url: path+'/funcionarios/?cmd=Details',
            data: 'id='+$(this).data('id'),
            contentType: 'application/x-www-form-urlencoded',
            dataType: 'json'
        })
        .done(function (data) {
        	if (data.status) {
        		var image = data.employee.image.length == 0 ? 'assets/image/images.png' : 'uploads/'+data.employee.image;
        		$('#details-img').attr('src', image);
        		$('#details-name').text(data.employee.firstName + ' ' + data.employee.lastName);
        		$('#details-id').text(data.employee.id);
        		$('#details-dpt').text(data.employee.department);
        		$('#details-email').text(data.employee.email);
        		$('#modal-details').modal('show');
        	}
        })
        .fail(function (jqXHR, textStatus) {
        	console.log(textStatus);
        });
    });
	
    $('.table').on('click', '.btn-remove', function (evt) {
    	$('.btn-action').prop('disabled', false);
    	$('#frm-remove').find('input[name=id]').val($(this).data('id'));
    });
    
    $('#frm-remove').on('click', '.btn-action', function (evt) {
    	$('.btn-action').prop('disabled', true);
    	
    	$.ajax({
            type: 'POST',
            url: path+'/funcionarios/?cmd=Remove',
            data: $('#frm-remove').serialize(),
            contentType: 'application/x-www-form-urlencoded',
            dataType: 'json'
        })
        .done(function (data) {
        	$('#modal-remove').modal('hide');
        	$('.table').DataTable().draw('page');
        })
        .fail(function (jqXHR, textStatus) {
        	console.log(textStatus);
        });
    });
	
})(path);
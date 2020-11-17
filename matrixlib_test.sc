
import('matrixlib','is_matrix','create_matrix','matrix_set','matrix_get','matrix_get_size','matrix_print');



test_matrixlib() -> (

  //create_matrix
    test_matrix = create_matrix(1,2);
    //matrix itself
    if(type(test_matrix)!= 'map', return('Matrix is of wrong type'));
    //matrix is_matrix
    if(test_matrix:'is_matrix'!= true, return('create_matrix() does\'t return a matrix'));
    //matrix size
    if(type(test_matrix:'size')!= 'list', return('Matrix size is of wrong type'));
    if(test_matrix:'size':0 != 1, return('Matrix size has wrong number of rows'));
    if(test_matrix:'size':1 != 2, return('Matrix size has wrong number of columns'));
    //matrix matrix
    if(type(test_matrix:'matrix') != 'list', return('Matrix matrix is of wrong type'));
    if(length(test_matrix:'matrix')!= 2, return('Matrix matrix is of wrong size (rows)'));
    for(test_matrix:'matrix',
        if(length(_)!=3, return('Matrix matrix is of wrong size (columns) on row '+_i));
        if(_ != [0,0,0],return('Matrix is wrongly filled on row '+_i));
    );
    print(format('l create_matrix is successful'));

  //is_matrix
    if(is_matrix([0,2,45]),return('is_matrix works with a list'));
    if(is_matrix({}),return('is_matrix works with an empty map'));
    if(!is_matrix(test_matrix), return('is_matrix doesn\'t work with a proper matrix'));
    print(format('l is_matrix is successful'));

  //matrix_set
    matrix_set(test_matrix,0,2,'asdf');
    if(test_matrix:'matrix':0:2 != 'asdf', return('Matrix set is not working or sets wrong value'));
    if(matrix_set(test_matrix,0,20,'e') != 'Matrix index out of bounds', return('Matrix get out of bounds checker is borked (rows)'));
    if(matrix_set(test_matrix,20,1,'e') != 'Matrix index out of bounds', return('Matrix get out of bounds checker is borked (columns)'));

    print(format('l matrix_set is successful'));

  //matrix_get
    value = matrix_get(test_matrix,0,2);
    if(value != 'asdf', return('Matrix get is not getting'));
    if(matrix_get(test_matrix,0,20) != 'Matrix index out of bounds', return('Matrix get out of bounds checker is borked (rows)'));
    if(matrix_get(test_matrix,20,1) != 'Matrix index out of bounds', return('Matrix get out of bounds checker is borked (columns)'));
    print(format('l matrix_get is successful'));

  //matrix_get_size
    if(matrix_get_size(test_matrix) != [1,2], return('Matrix get size is wrong'));
    print(format('l matrix_get_size is successful'));

    //VISUALLY CHECK THIS ONE

    //this
    print(test_matrix);
    //should look like this
    matrix_print(test_matrix);
    return(format('l SUCCESS'));
);


print(test_matrixlib());
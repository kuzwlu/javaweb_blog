function whenError(a){
a.onerror = null;
//paras = document.getElementsByClassName('blogs');
//for(i=0;i<paras.length;i++){
//     //删除元素 元素.parentNode.removeChild(元素);
//    if (paras[i] != null) 
//          paras[i].parentNode.removeChild( paras[i]); 
//}
a.src = 'upload/err.png';
//console.log('图片出错的时候调用默认的图片');
}
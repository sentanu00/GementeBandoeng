var project = {"id":43675,"targetCollections":{"V2":{"targetCollectionUrl":"wtc/targetcollections_4.0.x.wtc"},"V3":{"targetCollectionUrl":"wtc/targetcollections_4.1.x.wtc"},"V1":{"targetCollectionUrl":"wtc/targetcollections_3.x.wtc"},"targetCollectionThumbnailUrl":"http://s3-eu-west-1.amazonaws.com/studio-live/848125/datasets/e017cad2-4fde-4851-9723-195d8bc7a545/dataset_gallery.png"},"targetCollectionUrl":"wtc/targetcollections.wtc","lastLocallyStored":"2016-06-20T19:25:12.005Z","targets":[{"id":"0.6607425487838184","augmentations":[{"zoom":0.8192831745866964,"width":58.885978173418806,"zOrder":1000,"type":"Image","url":"augmentation/images/JiwasrayaBandung.png","id":"bp_aug_6244210","height":42.404304934662996,"rotation":0,"name":"bp_aug_6244210","opacity":100,"clickUrl":"","y":26.918844146938405,"x":19.763869924427226}],"active":true,"scaleFactor":0.7099609375,"label":"marker2","zoomFactor":100,"size":{"height":1024,"width":1024}}]};

if ( __SDK.checkVersion({minSDKVersion: {ios: "3.2", android: "3.2"}/*, requiredFeatures: ["3d"]*/}) ) {
    var converter = new blueprint.Converter(null, {report : false});
    converter.convertProject(project);
}
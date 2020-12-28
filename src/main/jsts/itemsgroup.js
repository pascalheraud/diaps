"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
var vue_class_component_1 = __importDefault(require("vue-class-component"));
var diapslib_1 = __importDefault(require("./diapslib"));
var ItemsGroupComponent = /** @class */ (function (_super) {
    __extends(ItemsGroupComponent, _super);
    function ItemsGroupComponent() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.editedBilanItem = null;
        _this.scrollPosition = 0;
        return _this;
    }
    ItemsGroupComponent.prototype.editBilanItem = function (bilanItem) {
        this.scrollPosition = window.pageYOffset;
        this.editedBilanItem = this.deepCopy(bilanItem);
        this.$nextTick(function () { this.$refs['description'].focus(); });
    };
    ItemsGroupComponent.prototype.cancelEditBilanItem = function () {
        this.editedBilanItem = null;
        this.$nextTick(function () {
            window.scrollTo(0, this.$data.scrollPosition);
        });
    };
    ItemsGroupComponent.prototype.updateBilanItem = function () {
        this.callApi('/apis/bilan/item/update', this.editedBilanItem).then(this.onUpdatedBilanItem);
    };
    ItemsGroupComponent.prototype.onUpdatedBilanItem = function (result) {
        this.$emit("reload", this.cancelEditBilanItem);
    };
    ItemsGroupComponent.prototype.formeTotal = function (allItems, itemsGroupDys) {
        var result = 0;
        for (var _i = 0, _a = Object.keys(allItems); _i < _a.length; _i++) {
            var key = _a[_i];
            var items = allItems[key];
            items.forEach(function (bilanItem) {
                if (itemsGroupDys) {
                    result += bilanItem.note * bilanItem.item.dysRate;
                }
                else {
                    result += bilanItem.note * bilanItem.item.rate;
                }
            });
        }
        return result;
    };
    ItemsGroupComponent.prototype.itemLabel = function (bilanItem) {
        var result;
        if (this.$props['itemsGroupDys']) {
            result = '' + bilanItem.item.dysNumber;
            if (bilanItem.item.category != 'OTHER') {
                result += ' (' + this.getPrefix(bilanItem.item) + '' + bilanItem.item.number + ')';
            }
        }
        else {
            result = this.getPrefix(bilanItem.item) + '' + bilanItem.item.number;
        }
        return result;
    };
    ItemsGroupComponent.prototype.getPrefix = function (item) {
        return item.category == 'EF' ? 'F' : 'M';
    };
    ItemsGroupComponent = __decorate([
        vue_class_component_1.default({
            name: 'itemsgroup',
            template: '#items-group-template',
            props: ["itemsGroupTitle", "itemsGroupPrefix", "itemsGroupItems", "itemsGroupDys"]
        })
    ], ItemsGroupComponent);
    return ItemsGroupComponent;
}(diapslib_1.default));
exports.default = ItemsGroupComponent;
//# sourceMappingURL=itemsgroup.js.map
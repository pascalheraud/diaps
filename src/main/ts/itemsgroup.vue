<template>
  <div>
    <section>
      <table class="table">
        <thead>
          <tr>
            <th>Item</th>
            <th>Note</th>
            <th>Coef</th>
            <th>Total</th>
            <th>Description</th>
            <td></td>
          </tr>
        </thead>
        <tbody v-for="(allItems, key) in itemsGroupItems">
          <tr v-if="key=='PAGE'">
            <td colspan="6" class="item-group">LA PAGE</td>
          </tr>
          <tr v-if="key=='MALADRESSE'">
            <td colspan="6" class="item-group">LA MALADRESSE</td>
          </tr>
          <tr v-if="key=='FORMES_ET_PROPORTIONS'">
            <td colspan="6" class="item-group">ERREURS DE FORMES ET DE PROPORTIONS</td>
          </tr>
          <tr v-for="bilanItem in allItems">
            <td>{{itemLabel(bilanItem)}} {{bilanItem.item.label}}</td>
            <td>
              <a href="#" @click="editBilanItem(bilanItem)">{{bilanItem.note}}</a>
            </td>
            <td v-if="itemsGroupDys">{{bilanItem.item.dysRate}}</td>
            <td v-else>{{bilanItem.item.rate}}</td>
            <td v-if="itemsGroupDys">{{bilanItem.item.dysRate * bilanItem.note}}</td>
            <td v-else>{{bilanItem.item.rate * bilanItem.note}}</td>
            <td class="description-table">
              <a
                href="#"
                @click="editBilanItem(bilanItem)"
              >{{bilanItem.description?bilanItem.description:'&#160;'}}</a>
            </td>
            <td>
              <span class="icon">
                <a href="#" @click="editBilanItem(bilanItem)">
                  <i class="fas fa-edit"></i>
                </a>
              </span>
            </td>
          </tr>
        </tbody>
        <tr>
          <td></td>
          <td></td>
          <td>Total :</td>
          <td>{{formeTotal(itemsGroupItems,itemsGroupDys)}}</td>
          <td></td>
        </tr>
        <template v-if="itemsGroupDys">
          <tr v-bind:class="{'dys-result':formeTotal(itemsGroupItems,itemsGroupDys) >=19}">
            <td>Très dysgraphique:</td>
            <td>sup à 19</td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr
            v-bind:class="{'dys-result':(formeTotal(itemsGroupItems,itemsGroupDys) >=14 && formeTotal(itemsGroupItems,itemsGroupDys) <19)}"
          >
            <td>Dysgraphique :</td>
            <td>sup à 14</td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          <tr
            v-bind:class="{'dys-result':(formeTotal(itemsGroupItems,itemsGroupDys)>=10 && formeTotal(itemsGroupItems,itemsGroupDys) <14)}"
          >
            <td>Suspecte :</td>
            <td>sup 10</td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
        </template>
      </table>
    </section>
    <div class="modal" v-if="editedBilanItem" v-bind:class="{'is-active':editedBilanItem!=null}">
      <div class="modal-background"></div>
      <div class="modal-card">
        <header class="modal-card-head">
          <p
            class="modal-card-title"
          >Modification de la note de "{{itemLabel(editedBilanItem)}} {{editedBilanItem.item.label}}"</p>
          <button class="delete" aria-label="close" @click="cancelEditBilanItem()"></button>
        </header>
        <section class="modal-card-body">
          <form onsubmit="return false;" @submit="updateBilanItem()">
            <div class="field">
              <label class="label">Description</label>
              <div class="control">
                <textarea
                  tabindex="1"
                  cols="60"
                  rows="7"
                  ref="description"
                  id="field-first-name"
                  type="text"
                  v-model="editedBilanItem.description"
                  max="1000"
                ></textarea>
              </div>
            </div>
            <div class="field">
              <label class="label">Note</label>
              <div class="control">
                <label class="radio">
                  <input
                    tabindex="2"
                    id="noteZero"
                    type="radio"
                    name="note"
                    value="0"
                    v-model.number="editedBilanItem.note"
                    v-on:keydown.enter.prevent="goFocus('#btnSubmit')"
                  />
                  0
                </label>
                <label class="radio">
                  <input
                    tabindex="2"
                    id="noteZero"
                    type="radio"
                    name="note"
                    value="0.5"
                    v-model.number="editedBilanItem.note"
                    v-on:keydown.enter.prevent="goFocus('#btnSubmit')"
                  />
                  0.5
                </label>
                <label class="radio">
                  <input
                    tabindex="2"
                    id="noteZero"
                    type="radio"
                    name="note"
                    value="1"
                    v-model.number="editedBilanItem.note"
                    v-on:keydown.enter.prevent="goFocus('#btnSubmit')"
                  />
                  1
                </label>
              </div>
            </div>
            <div class="field is-grouped">
              <div class="control">
                <button class="button is-secondary" @click="cancelEditBilanItem()">Annuler</button>
                <button id="btnSubmit" tabindex="3" class="button is-primary formSubmit">Enregistrer</button>
              </div>
            </div>
          </form>
        </section>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Component from "vue-class-component";
import BaseVueJS from "./diapslib";
import {BilanItem} from './jsweet/ts/io/github/pascalheraud/diaps/db/BilanItem'

@Component({
  name: "itemsgroup",
  template: "#items-group-template",
  props: [
    "itemsGroupTitle",
    "itemsGroupPrefix",
    "itemsGroupItems",
    "itemsGroupDys"
  ]
})
export default class ItemsGroupComponent extends BaseVueJS {
  editedBilanItem: BilanItem|null = null;
  scrollPosition: number = 0;

  editBilanItem(bilanItem: BilanItem) {
    this.scrollPosition = window.pageYOffset;
    this.editedBilanItem = this.deepCopy(bilanItem);
    this.$nextTick(function() {
      (<HTMLElement>this.$refs["description"]).focus();
    });
  }

  cancelEditBilanItem() {
    this.editedBilanItem = null;
    this.$nextTick(function() {
      window.scrollTo(0, this.$data.scrollPosition);
    });
  }

  updateBilanItem() {
    this.callApi("/apis/bilan/item/update", this.editedBilanItem).then(
      this.onUpdatedBilanItem
    );
  }

  onUpdatedBilanItem(result: BilanItem) {
    this.$emit("reload", this.cancelEditBilanItem);
  }

  formeTotal(allItems: BilanItem[], itemsGroupDys: boolean): number {
    var result: number = 0;
    for (let key of Object.keys(allItems)) {
      const items = allItems[key];
      items.forEach((bilanItem: any) => {
        if (itemsGroupDys) {
          result += bilanItem.note * bilanItem.item.dysRate;
        } else {
          result += bilanItem.note * bilanItem.item.rate;
        }
      });
    }
    return result;
  }
  itemLabel(bilanItem: any): string {
    var result: string;
    if (this.$props["itemsGroupDys"]) {
      result = "" + bilanItem.item.dysNumber;
      if (bilanItem.item.category != "OTHER") {
        result +=
          " (" +
          this.getPrefix(bilanItem.item) +
          "" +
          bilanItem.item.number +
          ")";
      }
    } else {
      result = this.getPrefix(bilanItem.item) + "" + bilanItem.item.number;
    }
    return result;
  }
  getPrefix(item: any): string {
    return item.category == "EF" ? "F" : "M";
  }
}
</script>
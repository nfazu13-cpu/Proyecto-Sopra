package org.sopra.rogueguild.repository.model;

import org.sopra.rogueguild.repository.ShopRepository;

public class WorldEvent {

    private static int messageOptions = 10;
    private String finalMessage;

    public String getFinalMessage() {
        return finalMessage;
    }

    public boolean isGlobal() {
        return Math.random() > 0.5; // if 'isGlobal' > 0.5, event = global, else event = specific
    }

    public boolean declareType() {
        return Math.random() > 0.5; // if 'type' > 0.5, percentage = possitive, else percentage = negative
    }

    public ItemCategory selectCategory() {
        ItemCategory[] categories = ItemCategory.values();
        int randomCategory = (int) (Math.random() * categories.length);
        return categories[randomCategory];
    }

    public double calcFactor() {
        return Math.round(((int) (Math.random() * 9) + 2) * 0.05 * 100.0) / 100.0;
    }

    public void randomWorldEvent(ShopRepository stock) {

        ItemCategory category = null;
        boolean isGlobal = isGlobal();
        boolean type = declareType();
        double factor = calcFactor();
        double percentage;

        if (type) {
            percentage = 1 + factor;
        } else {
            percentage = 1 - factor;
        }

        if (!isGlobal) {
            category = selectCategory();
        }

        modifyPrice(isGlobal, percentage, stock, category);

    }

    public void modifyPrice(boolean isGlobal, double percentage, ShopRepository stock, ItemCategory category) {

        int randomMessageOption = (int) (Math.random() * messageOptions) + 1;

        if (isGlobal) {

            globalApply(randomMessageOption, percentage, stock);

        } else {

            localApply(randomMessageOption, percentage, stock, category);

        }

    }

    public void globalApply(int option, double percentage, ShopRepository stock) {

        int eventPrice;

        for (Item item : stock.getAllStock().values()) {

            eventPrice = (int) (item.getBasePrice() * percentage);
            item.setPrice(eventPrice);
        }

        selectMessage(option, percentage);

    }

    public ItemCategory localApply(int option, double percentage, ShopRepository stock, ItemCategory category) {

        int eventPrice;

        for (Item item : stock.getAllStock().values()) {

            if (item.getCategory() == category) {

                eventPrice = (int) (item.getBasePrice() * percentage);
                item.setPrice(eventPrice);
            }

        }

        selectMessage(option, percentage, category);

        return category;

    }

    public void selectMessage(int messageSelection, double percentage) {

        if (percentage > 1) {
            percentage -= 1;
            percentage *= 100;
            percentage = Math.round(percentage);

            switch (messageSelection) {
                case 1:
                    this.finalMessage = "¡Las guerras de los reinos del Norte han provocado una subida del "
                            + percentage + "% en todos los productos!";
                    break;

                case 2:
                    this.finalMessage = "¡Una plaga de dragones rojos acecha los caminos! Toda la mercancía sube un "
                            + percentage + "% debido al alto riesgo del transporte.";
                    break;

                case 3:
                    this.finalMessage = "¡El Rey ha decretado un nuevo impuesto sobre el comercio en todo el reino! Todos los precios de la tienda aumentan un "
                            + percentage + "%.";
                    break;

                case 4:
                    this.finalMessage = "¡Una terrible sequía azota la región! Los costes de mantenimiento obligan a subir todos los productos un "
                            + percentage + "%.";
                    break;

                case 5:
                    this.finalMessage = "¡El gremio de transportistas y arrieros se declara en huelga! Comprar cualquier cosa cuesta un "
                            + percentage + "% más.";
                    break;

                case 6:
                    this.finalMessage = "¡Se rumorea la llegada de un ejército orco a las fronteras! El pánico generalizado infla todos los precios un "
                            + percentage + "%.";
                    break;

                case 7:
                    this.finalMessage = "¡Una maldición de óxido y podredumbre se extiende por los almacenes imperiales! Reponer los artículos cuesta un "
                            + percentage + "% más.";
                    break;

                case 8:
                    this.finalMessage = "¡La devaluación de la moneda real por orden de la corona hace que el coste de vida suba un "
                            + percentage + "%!";
                    break;

                case 9:
                    this.finalMessage = "¡Los bandidos han tomado el paso de montaña principal! Los mercaderes cobran un "
                            + percentage + "% extra por los desvíos peligrosos.";
                    break;

                case 10:
                    this.finalMessage = "¡Alineación planetaria desfavorable! La magia del comercio se debilita y provoca un aumento general del "
                            + percentage + "%.";
                    break;

                default:
                    this.finalMessage = "Las tensiones en el reino hacen que el mercado global suba de precio.";
                    break;

            }

        } else {
            percentage = 1 - percentage;
            percentage *= 100;
            percentage = Math.round(percentage);

            switch (messageSelection) {
                case 1:
                    this.finalMessage = "¡Los caminos comerciales del Norte vuelven a ser seguros! Los precios globales bajan un "
                            + percentage + "%.";
                    break;

                case 2:
                    this.finalMessage = "¡Un grupo de aventureros ha derrotado al dragón de la montaña! Los mercaderes lo celebran bajando todos los precios un "
                            + percentage + "%.";
                    break;

                case 3:
                    this.finalMessage = "¡Es el Día del Festival del Solsticio en la ciudad! Los tenderos ofrecen una rebaja global del "
                            + percentage + "% en todo su inventario.";
                    break;

                case 4:
                    this.finalMessage = "¡Se ha firmado un tratado de paz histórico entre los reinos vecinos! La apertura de fronteras abarata todo un "
                            + percentage + "%.";
                    break;

                case 5:
                    this.finalMessage = "¡Una megacarabana de comerciantes del lejano oriente ha llegado a la ciudad! La sobreoferta baja los precios un "
                            + percentage + "%.";
                    break;

                case 6:
                    this.finalMessage = "¡El nuevo año del Duque comienza con la exención de aranceles comerciales! Todo el inventario se reduce un "
                            + percentage + "%.";
                    break;

                case 7:
                    this.finalMessage = "¡Año de cosechas y producción récord en todas las profesiones! Los costes bajan y la tienda aplica un "
                            + percentage + "% de descuento en todo.";
                    break;

                case 8:
                    this.finalMessage = "¡El gremio de magos ha abierto un portal permanente con la capital! Al eliminar el transporte, todo cae un "
                            + percentage + "%.";
                    break;

                case 9:
                    this.finalMessage = "¡Liquidación por reforma del establecimiento! El mercader quiere vaciar las estanterías rápido y aplica un "
                            + percentage + "% general.";
                    break;

                case 10:
                    this.finalMessage = "¡Bendición del Dios del Comercio sobre toda la comarca! La fortuna sonríe a los compradores y todo baja un "
                            + percentage + "%.";
                    break;

                default:
                    this.finalMessage = "Un periodo de calma y prosperidad estabiliza los precios del mercado a la baja.";
                    break;

            }

        }

    }

    public String customMessage(ItemCategory category) {

        String customMessage = "";

        switch (category) {
            case WEAPON:
                customMessage = "las armas ";
                break;

            case ARMOR:
                customMessage = "las armaduras ";
                break;

            case POTION:
                customMessage = "las pociones ";
                break;

            case HELMET:
                customMessage = "los cascos ";
                break;

            case BOOTS:
                customMessage = "las botas ";
                break;

            default:
                customMessage = "otros productos ";
                break;
        }

        return customMessage;

    }

    public void selectMessage(int option, double percentage, ItemCategory category) {

        if (percentage > 1) {
            percentage -= 1;
            percentage *= 100;
            percentage = Math.round(percentage);

            switch (option) {
                case 1:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha subido un " + percentage
                            + "% debido a la escasez de materias primas en los suministros de los artesanos!";
                    break;

                case 2:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha subido un " + percentage
                            + "%! El Gran Gremio ha impuesto nuevas tasas de fabricación obligatorias.";
                    break;

                case 3:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha subido un " + percentage
                            + "% porque el ejército del Rey ha confiscado la mitad de las existencias para sus filas.";
                    break;

                case 4:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha subido un " + percentage
                            + "% debido al repentino asalto y saqueo de los principales talleres de producción.";
                    break;

                case 5:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha subido un " + percentage
                            + "%! Una nueva regulación clerical prohíbe su venta a no iniciados sin pagar un extra.";
                    break;

                case 6:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha subido un " + percentage
                            + "% tras un sabotaje mágico que ha inutilizado los almacenes específicos del gremio.";
                    break;

                case 7:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha subido un " + percentage
                            + "% por el aumento en los costes de transporte desde las lejanas tierras de producción.";
                    break;

                case 8:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha subido un " + percentage
                            + "%! Los maestros especialistas de este sector exigen salarios más altos debido al peligro de la región.";
                    break;

                case 9:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha subido un " + percentage
                            + "% debido a la alta demanda de los aventureros locales antes de la gran incursión.";
                    break;

                case 10:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha subido un " + percentage
                            + "%! Una extraña perturbación en el plano místico dificulta su creación.";
                    break;

                default:
                    this.finalMessage = "La escasez local aumenta el valor de este sector.";
                    break;
            }

        } else {
            percentage = 1 - percentage;
            percentage *= 100;
            percentage = Math.round(percentage);

            switch (option) {
                case 1:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha bajado un " + percentage
                            + "% gracias al descubrimiento de un nuevo yacimiento de materiales!";
                    break;

                case 2:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha bajado un " + percentage
                            + "%! Los artesanos locales han refinado sus técnicas, reduciendo los tiempos de creación.";
                    break;

                case 3:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha bajado un " + percentage
                            + "% debido a que el ejército ha devuelto el excedente de las guarniciones al mercado.";
                    break;

                case 4:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha bajado un " + percentage
                            + "% tras la llegada masiva de refugiados de talleres vecinos dispuestos a comerciar a bajo coste.";
                    break;

                case 5:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha bajado un " + percentage
                            + "%! El Ducado ha eliminado temporalmente los aranceles específicos para promover su uso.";
                    break;

                case 6:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha bajado un " + percentage
                            + "% gracias a un hechizo de duplicación en masa que salió bien en los almacenes.";
                    break;

                case 7:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha bajado un " + percentage
                            + "%! Se han abierto nuevas y más eficientes rutas comerciales libres de monstruos.";
                    break;

                case 8:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha bajado un " + percentage
                            + "% porque los aprendices del gremio han lanzado una producción masiva para validar sus títulos.";
                    break;

                case 9:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha bajado un " + percentage
                            + "% debido a un claro exceso de stock acumulado que el comerciante necesita liquidar ya.";
                    break;

                case 10:
                    this.finalMessage = "¡El precio de " + customMessage(category) + "ha bajado un " + percentage
                            + "%! Una bendición de la escuela de transmutación facilita mágicamente su ensamblaje.";
                    break;

                default:
                    this.finalMessage = "La sobreproducción hace que este tipo de bienes sea más accesible.";
                    break;
            }

        }

    }

}

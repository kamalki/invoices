class AddtrainIdColumnToRoute < ActiveRecord::Migration
  def change
  add_column :routes, :train_id, :integer
  end
end
